package za.co.vzap.Interface.Service;

import za.co.vzap.Report.Model.CustomerReportsDto;
import za.co.vzap.Report.Model.LeastPerformingStoresDto;
import za.co.vzap.Report.Model.StoreSalesDto;
import za.co.vzap.Report.Model.ProductSalesDto;
import za.co.vzap.Report.Model.StoresAtTargetDto;
import za.co.vzap.Report.Model.TopAchievingStoresDto;
import za.co.vzap.Report.Model.TopEmployeesDto;
import za.co.vzap.Report.Model.TopFourtyProductsDto;
import za.co.vzap.Interface.Model.IEntity;
import za.co.vzap.Report.Model.StatementDto;

public interface IReportService {
    TopAchievingStoresDto topAchievingStores();
    
    CustomerReportsDto getCustomerReport(String month, int resultAmount);
    
    StoreSalesDto storeSalesByMonth(String branchId, String month);
    
    TopEmployeesDto topSellingEmployees(String branchId);
    
    StoresAtTargetDto storesAtTarget();
    
    TopFourtyProductsDto getTop40Products();
    
    LeastPerformingStoresDto getLeastPerforming(int interval);
    
    ProductSalesDto getProductSales();
    
    StoreSalesDto storeDailySales(String branchId);
    
    String downloadCurrentReport(StatementDto dto);
}
