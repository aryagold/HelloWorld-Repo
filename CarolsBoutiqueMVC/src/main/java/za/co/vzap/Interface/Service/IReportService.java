package za.co.vzap.Interface.Service;

import za.co.vzap.Model.Report.CustomerReportsDto;
import za.co.vzap.Model.Report.LeastPerformingStoresDto;
import za.co.vzap.Model.Report.StoreSalesDto;
import za.co.vzap.Model.Report.ProductSales;
import za.co.vzap.Model.Report.StoresAtTargetDto;
import za.co.vzap.Model.Report.TopAchievingStoresDto;
import za.co.vzap.Model.Report.TopEmployeesDto;
import za.co.vzap.Model.Report.TopFourtyProducts;
import za.co.vzap.Interface.Model.IEntity;

public interface IReportService {

    TopAchievingStoresDto topAchievingStores();

    CustomerReportsDto getCustomerReport(String month, int resultAmount);

    StoreSalesDto storeSalesByMonth(String branchId, String month);

    TopEmployeesDto topSellingEmployees(String branchId);

    StoresAtTargetDto storesAtTarget(String month);

    TopFourtyProducts getTop40Products();

    LeastPerformingStoresDto getLeastPerforming(int interval);

    ProductSales getProductSales();

    StoreSalesDto storeDailySales(String branchId);

}
