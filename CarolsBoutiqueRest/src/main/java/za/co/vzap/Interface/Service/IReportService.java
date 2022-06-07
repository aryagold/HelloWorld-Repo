package za.co.vzap.Interface.Service;

import za.co.vzap.Report.Model.CustomerReports;
import za.co.vzap.Report.Model.DailySales;
import za.co.vzap.Report.Model.LeastPerformingStores;
import za.co.vzap.Report.Model.MonthlySales;
import za.co.vzap.Report.Model.ProductSales;
import za.co.vzap.Report.Model.TargetStores;
import za.co.vzap.Report.Model.TopAchievingStores;
import za.co.vzap.Report.Model.TopEmployees;
import za.co.vzap.Report.Model.TopFourtyProducts;
import za.co.vzap.Sale.Model.IEntity;

public interface IReportService {
    TopAchievingStores getTopAchievingStores();
    
    CustomerReports getCustomerReport();
    
    MonthlySales getMonthlySales();
    
    TopEmployees getTopEmployees();
    
    TargetStores getStoresAtTarget();
    
    TopFourtyProducts getTop40Products();
    
    LeastPerformingStores getLeastPerforming();
    
    ProductSales getProductSales();
    
    DailySales getDailySales();
    
    String downloadCurrentReport();
}
