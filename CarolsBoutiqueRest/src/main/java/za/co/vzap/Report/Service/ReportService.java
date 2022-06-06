package za.co.vzap.Report.Service;

import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Report.Model.ItemAmount;
import za.co.vzap.Report.Model.TopAchievingStores;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Repository.SaleRepository;

public class ReportService implements IReportService {
    private IRepository saleRepository = new SaleRepository();

    @Override
    public void viewCustomerReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewMonthlySales() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewTopEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewTop40() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewLeastPerforming() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewProductReport(IEntity arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewDailySales(IEntity arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void downloadCurrentReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TopAchievingStores getTopAchievingStores() {
        ItemAmount itemAmount = null;
        
//        List<ItemAmount> storeSales = saleRepository.getWhere("", arg1);
        
//        for(ItemAmount item : storeSales) {
//           
//        }
        
        return null;
    }
    
}
