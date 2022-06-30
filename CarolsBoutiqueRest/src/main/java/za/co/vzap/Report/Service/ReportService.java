package za.co.vzap.Report.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Report.Model.CustomerReportsDto;
import za.co.vzap.Report.Model.ItemAmount;
import za.co.vzap.Report.Model.LeastPerformingStoresDto;
import za.co.vzap.Report.Model.StoreSalesDto;
import za.co.vzap.Report.Model.ProductSalesDto;
import za.co.vzap.Report.Model.SaleSummaryDto;
import za.co.vzap.Report.Model.StoresAtTargetDto;
import za.co.vzap.Report.Model.TopAchievingStoresDto;
import za.co.vzap.Report.Model.TopEmployeesDto;
import za.co.vzap.Report.Model.TopFourtyProductsDto;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

public class ReportService implements IReportService {
    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    private IRepository userRepository;
    private IRepository branchRepository;

    public ReportService() {
        userRepository = new UserRepository();
        branchRepository = new BranchRepository();
        
        try {
//            prop.load(new FileInputStream("MySQL.properties"));

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carolsboutique?useSSL=false", "root", "rootroot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public TopAchievingStoresDto topAchievingStores() {
        TopAchievingStoresDto dto = new TopAchievingStoresDto();
        dto.title = "Top Achieving Stores";
        
        String statement =
                "select branch.name, sum(product.price) as totalSales " +
                "from sale " + 
                "join salelineitem on sale.id = salelineitem.saleId " +
                "join inventory on salelineitem.inventoryId = inventory.id " +
                "join product on inventory.productId = product.id " +
                "join branch on inventory.branchId = branch.id " +
                "group by branch.name " +
                "order by sum(product.price) DESC";
       
        
        if (con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();
                
                while (rs.next()) {
                    ItemAmount storeSale = new ItemAmount();

                    storeSale.description = rs.getString("name");
                    storeSale.amount = rs.getDouble("totalSales");

                    dto.storeSales.add(storeSale);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                
                closeStreams(ps, rs);
                
            }
        }
        
        return dto;
    }
    
    @Override
    public CustomerReportsDto getCustomerReport(String month, int resultAmount) {
        CustomerReportsDto dto = new CustomerReportsDto();
        dto.title = "Average Customer Ratings by Store";
        
        dto.date = month;
        dto.resultAmount = resultAmount;
        
        String statement
                = "select branch.name, avg(rating) as average\n"
                + "                from review\n"
                + "                join branch on review.branchId = branch.id\n"
                + "                where MONTHNAME(review.date) = '" + month + "'\n"
                + "                group by branch.name\n"
                + "                order by average desc\n"
                + "                limit 0," + resultAmount;
        
        
        if (con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while (rs.next()) {
                    ItemAmount customerReport = new ItemAmount();

                    customerReport.description = rs.getString("branch.name");
                    customerReport.amount = rs.getInt("average");
                       
                    dto.branchName = customerReport.description;

                    dto.storeRatings.add(customerReport);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                
                closeStreams(ps, rs);
                
            }
        }

        return dto;
    }

    @Override
    public StoreSalesDto storeSalesByMonth(String branchId, String month) {
        StoreSalesDto dto = new StoreSalesDto();
        dto.title = "Sales By Month";
        
        SaleSummaryDto sale = new SaleSummaryDto();
        
        Branch branch = (Branch) branchRepository.getById(branchId);
        dto.branchName = branch.getName();
        dto.date = month;

        String statement
                = "select sale.id, sale.date, sale.userId, sum(product.price) as total \n"
                + "                 from sale \n"
                + "                 join salelineitem on sale.id = salelineitem.saleId\n"
                + "                 join inventory on salelineitem.inventoryId = inventory.id\n"
                + "                 join product on inventory.productId = product.id\n"
                + "                 join branch on inventory.branchId = branch.id\n"
                + "                 where branchId = '" + branchId + "' and MONTHNAME(sale.date) = '" + month + "'\n"
                + "                 group by sale.id, sale.date, sale.userId \n"
                + "                 order by sale.date";

        if (con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while (rs.next()) {
                    

                    sale.saleId = rs.getString("sale.id");
                    sale.date = rs.getTimestamp("date").toString();
                    sale.userId = rs.getString("userId");
                    
                    User user = (User) userRepository.getById(sale.userId);
                    sale.employee = user.getName();
                    
                    sale.total = rs.getDouble("total");
                    dto.totalSales += sale.total;
                    dto.sales.add(sale);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                
                closeStreams(ps, rs);
                
            }
        }

        return dto;
    }

    @Override
    public TopEmployeesDto topSellingEmployees(String branchId) {
        TopEmployeesDto dto = new TopEmployeesDto();
        dto.title = "Top Selling Employees";
        
        dto.branchName = "";
        
        if(branchId != null) {
            Branch branch = (Branch) branchRepository.getById(branchId);
            dto.branchName = branch.getName();
        }

        String statement
                = "select user.id, user.name, sum(product.price) as total\n"
                + "from sale\n"
                + "join salelineitem on sale.id = salelineitem.saleId\n"
                + "join inventory on salelineitem.inventoryId = inventory.id\n"
                + "join product on inventory.productId = product.id\n"
                + "join user on sale.userId = user.id\n";
        
        if(branchId != null) {
            statement = statement + "where user.branchId = '" + branchId + "'\n";
        }

        statement = statement 
                + "group by user.id, user.name\n"
                + "order by total desc";
        

        if (con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while (rs.next()) {
                    ItemAmount employeeSale = new ItemAmount();
                    
                    employeeSale.description = rs.getString("user.name");
                    employeeSale.amount = rs.getDouble("total");
                        
                    dto.employeeSales.add(employeeSale);
                        
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                
                closeStreams(ps, rs);
                
            }
        }

        return dto;
    }
    
    @Override
    public StoresAtTargetDto storesAtTarget(String month) {
        StoresAtTargetDto dto = new StoresAtTargetDto();
        dto.title = "Stores At Target";

        String statement
                = "select branch.id, branch.name, branch.monthlyTarget, sum(product.price) as total\n"
                + "from sale\n"
                + "join salelineitem on sale.id = salelineitem.saleId\n"
                + "join inventory on salelineitem.inventoryId = inventory.id\n"
                + "join product on inventory.productId = product.id\n"
                + "join branch on inventory.branchId = branch.id\n"
                + "where MONTHNAME(sale.date) = '" + month + "' "
                + "group by branch.id, branch.name, branch.monthlyTarget\n"
                + "having total >= branch.monthlyTarget\n"
                + "order by total desc";
        

        if (con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while (rs.next()) {
                    ItemAmount storeSale = new ItemAmount();

                    storeSale.description = rs.getString("name");
                    storeSale.amount = rs.getDouble("monthlyTarget");

                    dto.storeSales.add(storeSale);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                
                closeStreams(ps, rs);
                
            }
        }

        return dto;
    }

    @Override
    public TopFourtyProductsDto getTop40Products() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeastPerformingStoresDto getLeastPerforming(int interval) {
        LeastPerformingStoresDto dto = new LeastPerformingStoresDto();
        dto.title = "Least Performing Stores";
        
        String statement = "select branch.name, sum(product.price) as total \n"
                + "                 from sale \n"
                + "                join salelineitem on sale.id = salelineitem.saleId \n"
                + "                join inventory on salelineitem.inventoryId = inventory.id \n"
                + "                join product on inventory.productId = product.id \n"
                + "                join branch on inventory.branchId = branch.id \n"
                + "                where sale.date > DATE_SUB(now(), INTERVAL " + interval + " MONTH)\n"
                + "                group by branch.name \n"
                + "                order by total ";
    
        
        if (con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while (rs.next()) {
                    ItemAmount storeSale = new ItemAmount();

                    storeSale.description = rs.getString("branch.name");
                    storeSale.amount = rs.getDouble("total");

                    dto.storeSales.add(storeSale);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                
                closeStreams(ps, rs);
                
            }
        }

        return dto;
        
        
    }

    @Override
    public ProductSalesDto getProductSales() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StoreSalesDto storeDailySales(String branchId) {
        StoreSalesDto dto = new StoreSalesDto();
        dto.title = "Sales By Day";
        
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dto.date = dateFormat.format(currentDate);

        Branch branch = (Branch) branchRepository.getById(branchId);
        dto.branchName = branch.getName();
        
        dto.dailyTarget = branch.getDailyTarget();

        String statement
                = "select sale.id, sale.date, sale.userId, sum(product.price) as total "
                + "from sale "
                + "join salelineitem on sale.id = salelineitem.saleId "
                + "join inventory on salelineitem.inventoryId = inventory.id "
                + "join product on inventory.productId = product.id "
                + "join branch on inventory.branchId = branch.id "
                + "where branchId = '" + branchId + "' and date(sale.date) = current_date "
                + "group by sale.id, sale.date, sale.userId "
                + "order by sale.date";
        
        

        if (con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while (rs.next()) {
                    SaleSummaryDto sale = new SaleSummaryDto();

                    sale.saleId = rs.getString("id");
                    sale.date = rs.getTimestamp("date").toString();
                    sale.userId = rs.getString("userId");

                    User user = (User) userRepository.getById(sale.userId);
                    sale.employee = user.getName();

                    sale.total = rs.getDouble("total");

                    dto.sales.add(sale);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                
                closeStreams(ps, rs);
                
            }
        }

        return dto;
    }

    public byte[] downloadTopStoresReport() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        TopAchievingStoresDto dto = this.topAchievingStores();
        
        try {
            Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
            PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()
            
            document.open();
            
            document.addTitle(dto.title);
          
            PdfPTable table = new PdfPTable(2);
            table.addCell("Store Name");
            table.addCell("Total Sales");
            
            for(ItemAmount storeSale : dto.storeSales) {
                table.addCell(new PdfPCell(new Phrase(storeSale.description)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(storeSale.amount))));
            }
            
            document.add(table);
            
            document.close();
            
            
        } catch (DocumentException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] downloadCustomerReport(String month, int resultAmount) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        CustomerReportsDto dto = this.getCustomerReport(month, resultAmount);

        try {
            Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
            PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

            document.open();

            document.addTitle(dto.title);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Store Name");
            table.addCell("Rating");

            for (ItemAmount storeSale : dto.storeRatings) {
                table.addCell(new PdfPCell(new Phrase(storeSale.description)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(storeSale.amount))));
            }

            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] downloadMonthSalesReport(String branchId, String month) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        StoreSalesDto dto = this.storeSalesByMonth(branchId, month);

        try {
            Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
            PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

            document.open();

            document.addTitle(dto.title);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Store Name");
            table.addCell("Total");

            for (SaleSummaryDto storeSale : dto.sales) {
                table.addCell(new PdfPCell(new Phrase(dto.branchName)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(dto.getTotalSales()))));
            }

            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] downloadTopEmployeesReport(String branchId) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        TopEmployeesDto dto = this.topSellingEmployees(branchId);

        try {
            Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
            PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

            document.open();

            document.addTitle(dto.title);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Employee");
            table.addCell("Total");
            
            for (ItemAmount itemAmount : dto.employeeSales) {
                table.addCell(new PdfPCell(new Phrase(itemAmount.description)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(itemAmount.amount))));
            }

            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] downloadStoresAtTargetReport(String month) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        StoresAtTargetDto dto = this.storesAtTarget(month);

        try {
            Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
            PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

            document.open();

            document.addTitle(dto.title);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Store");
            table.addCell("Target");

            for (ItemAmount itemAmount : dto.storeSales) {
                table.addCell(new PdfPCell(new Phrase(itemAmount.description)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(itemAmount.amount))));
            }

            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] downloadLeastPerformingReport(int interval) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        LeastPerformingStoresDto dto = this.getLeastPerforming(interval);

        try {
            Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
            PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

            document.open();

            document.addTitle(dto.title);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Store Name");
            table.addCell("Total");

            for (ItemAmount itemAmount : dto.storeSales) {
                table.addCell(new PdfPCell(new Phrase(itemAmount.description)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(itemAmount.amount))));
            }

            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] downloadDailySalesReport(String branchId) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        StoreSalesDto dto = this.storeDailySales(branchId);

        try {
            Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
            PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

            document.open();

            document.addTitle(dto.title);

            PdfPTable table = new PdfPTable(3);
            table.addCell("Date");
            table.addCell("Employee Name");
            table.addCell("Total");

            for (SaleSummaryDto itemAmount : dto.sales) {
                table.addCell(new PdfPCell(new Phrase(itemAmount.date)));
                table.addCell(new PdfPCell(new Phrase(itemAmount.employee)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(itemAmount.total))));
            }

            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void closeStreams(PreparedStatement ps, ResultSet rs) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
}
