package za.co.vzap.Interface.Service;

import za.co.vzap.Report.Model.TopAchievingStores;
import za.co.vzap.Sale.Model.IEntity;

public interface IReportService {
    TopAchievingStores getTopAchievingStores();
    
    void viewCustomerReport();
    
    void viewMonthlySales();
    
    void viewTopEmployees();
    
    void viewTop40();
    
    void viewLeastPerforming();
    
    void viewProductReport(IEntity entity);
    
    void viewDailySales(IEntity entity);
    
    void downloadCurrentReport();
}
