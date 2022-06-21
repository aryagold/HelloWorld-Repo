package za.co.vzap.Interface.Service;

import za.co.vzap.Model.Report.CustomerReports;
import za.co.vzap.Model.Report.LeastPerformingStores;
import za.co.vzap.Model.Report.StoreSalesDto;
import za.co.vzap.Model.Report.ProductSales;
import za.co.vzap.Model.Report.StoresAtTargetDto;
import za.co.vzap.Model.Report.TopAchievingStoresDto;
import za.co.vzap.Model.Report.TopEmployeesDto;
import za.co.vzap.Model.Report.TopFourtyProducts;
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
