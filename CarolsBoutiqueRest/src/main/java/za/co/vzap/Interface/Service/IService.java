package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Sale.Model.IEntity;

public interface IService {
    boolean addCustomer(String email , String phoneNumber);//here
    
    boolean addReview(String comment, int rating, String branchId);//here
    
    void updateToTeller(String userId);//here
    
    void addBranch(IEntity entity);//here
    
    IEntity login(String userId, String password);//here
    
    boolean addInventoryControl(InventoryControl inventoryControl, String barcode, String branchId, String size, int quantity);//here
    
    boolean addProduct(String productId, String name, String categoryId, double price);
    
    void addToSale(IEntity entity);
    
    void addRefund(IEntity entity);//here
    
    void confirmSale( IEntity entity);
    
    List<Inventory> findProduct(String productId);//here
    
    void deleteSaleLineItem(IEntity entity);//here
    
    void updateToReserved(IEntity entity);//here
    
    void requestIBT(IEntity entity);
    
    void acceptIBT(int Id);
    
    void IBTReceived(int Id);
    
    void viewCustomerReport();
    
    void viewMonthlySales();
    
    void viewTopEmployees();
    
    void viewTop40();
    
    void viewLeastPerforming();
    
    void viewProductReport(IEntity entity);
    
    void viewDailySales(IEntity entity);
    
    void downloadCurrentReport();
}
