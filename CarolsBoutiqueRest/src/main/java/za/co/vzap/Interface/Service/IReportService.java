package za.co.vzap.Interface.Service;

import za.co.vzap.Sale.Model.IEntity;

public interface IReportService {
    void viewCustomerReport();
    
    void viewMonthlySales();
    
    void viewTopEmployees();
    
    void viewTop40();
    
    void viewLeastPerforming();
    
    void viewProductReport(IEntity entity);
    
    void viewDailySales(IEntity entity);
    
    void downloadCurrentReport();
}
