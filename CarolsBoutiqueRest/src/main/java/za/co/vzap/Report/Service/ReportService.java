package za.co.vzap.Report.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Report.Model.CustomerReports;
import za.co.vzap.Report.Model.ItemAmount;
import za.co.vzap.Report.Model.LeastPerformingStores;
import za.co.vzap.Report.Model.StoreSalesDto;
import za.co.vzap.Report.Model.ProductSales;
import za.co.vzap.Report.Model.SaleSummaryDto;
import za.co.vzap.Report.Model.StoresAtTargetDto;
import za.co.vzap.Report.Model.TopAchievingStoresDto;
import za.co.vzap.Report.Model.TopEmployeesDto;
import za.co.vzap.Report.Model.TopFourtyProducts;
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
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        return dto;
    }
    
    @Override
    public CustomerReports getCustomerReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StoreSalesDto storeSalesByMonth(String branchId, String month) {
        StoreSalesDto dto = new StoreSalesDto();
        dto.title = "Sales By Month";
        
        Branch branch = (Branch) branchRepository.getById(branchId);
        dto.branchName = branch.getName();
        dto.date = month;

        String statement
                = "select sale.id, sale.date, sale.userId, sum(product.price) as total "
                + "from sale "
                + "join salelineitem on sale.id = salelineitem.saleId "
                + "join inventory on salelineitem.inventoryId = inventory.id "
                + "join product on inventory.productId = product.id "
                + "join branch on inventory.branchId = branch.id "
                + "where branchId = '" + branchId + "' and MONTHNAME(sale.date) = '" + month + "' "
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
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
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
                + "join branch on inventory.branchId = branch.id\n"
                + "join user on sale.userId = user.id\n";
        
        if(branchId != null) {
            statement = statement + "where branch.id = '" + branchId + "'\n";
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
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return dto;
    }
    
    @Override
    public StoresAtTargetDto storesAtTarget() {
        StoresAtTargetDto dto = new StoresAtTargetDto();
        dto.title = "Stores At Target";

        String statement
                = "select branch.id, branch.name, branch.monthlyTarget, sum(product.price) as total\n"
                + "from sale\n"
                + "join salelineitem on sale.id = salelineitem.saleId\n"
                + "join inventory on salelineitem.inventoryId = inventory.id\n"
                + "join product on inventory.productId = product.id\n"
                + "join branch on inventory.branchId = branch.id\n"
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
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return dto;
    }

    @Override
    public TopFourtyProducts getTop40Products() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeastPerformingStores getLeastPerforming() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductSales getProductSales() {
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
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return dto;
    }

    @Override
    public String downloadCurrentReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
