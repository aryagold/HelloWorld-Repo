package za.co.vzap.Interface.Service;

import za.co.vzap.Report.Model.CustomerReports;
import za.co.vzap.Report.Model.LeastPerformingStores;
import za.co.vzap.Report.Model.StoreSalesDto;
import za.co.vzap.Report.Model.ProductSales;
import za.co.vzap.Report.Model.StoresAtTargetDto;
import za.co.vzap.Report.Model.TopAchievingStoresDto;
import za.co.vzap.Report.Model.TopEmployeesDto;
import za.co.vzap.Report.Model.TopFourtyProducts;
import za.co.vzap.Interface.Model.IEntity;

public interface IReportService {
    TopAchievingStoresDto topAchievingStores();
    
    CustomerReports getCustomerReport();
    
    StoreSalesDto storeSalesByMonth(String branchId, String month);
    
    TopEmployeesDto topSellingEmployees(String branchId);
    
    StoresAtTargetDto storesAtTarget();
    
    TopFourtyProducts getTop40Products();
    
    LeastPerformingStores getLeastPerforming();
    
    ProductSales getProductSales();
    
    StoreSalesDto storeDailySales(String branchId);
    
    String downloadCurrentReport();
}
