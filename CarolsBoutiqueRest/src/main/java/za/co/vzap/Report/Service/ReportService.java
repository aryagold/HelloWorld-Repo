package za.co.vzap.Report.Service;

import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Report.Model.CustomerReports;
import za.co.vzap.Report.Model.DailySales;
import za.co.vzap.Report.Model.ItemAmount;
import za.co.vzap.Report.Model.LeastPerformingStores;
import za.co.vzap.Report.Model.MonthlySales;
import za.co.vzap.Report.Model.ProductSales;
import za.co.vzap.Report.Model.TargetStores;
import za.co.vzap.Report.Model.TopAchievingStores;
import za.co.vzap.Report.Model.TopEmployees;
import za.co.vzap.Report.Model.TopFourtyProducts;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Repository.SaleRepository;

public class ReportService implements IReportService {
    private IRepository saleRepository = new SaleRepository();

    
    @Override
    public TopAchievingStores getTopAchievingStores() {
        ItemAmount itemAmount = null;
        
//        List<ItemAmount> storeSales = saleRepository.getWhere("", arg1);
        
//        for(ItemAmount item : storeSales) {
//           
//        }
        
        return null;
    }
    
    @Override
    public CustomerReports getCustomerReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MonthlySales getMonthlySales() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TopEmployees getTopEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public TargetStores getStoresAtTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public DailySales getDailySales() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String downloadCurrentReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}
