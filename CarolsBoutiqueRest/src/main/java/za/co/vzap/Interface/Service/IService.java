package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.User.Model.User;

public interface IService {
    boolean addCustomer(String email , String phoneNumber);//here
    
    boolean addReview(String comment, int rating, String branchId);//here
    
    boolean updateToTeller(String userId);//here
    
    boolean addBranch(Branch branch);//here
    
    User login(String userId, String password);//here
    
    boolean addInventoryControl(InventoryControl inventoryControl, String barcode, String branchId, String size, int quantity);//here
    
    boolean addProduct(String productId, String name, String categoryId, double price);
    
    boolean addToSale(String productId, int quantity, String userId, String email, int paymentId, String branchId);
    
    boolean addRefund(String barcode, int quantity);//here
    
    void confirmSale(Sale sale);
    
    void cancelSale(Sale sale);
    
    void email(IEntity entity);
    
    List<Inventory> findProduct(String productId);//here
    
    boolean deleteSaleLineItem(Product product);//here
    
    boolean updateToReserved(String saleID);//here
    
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
