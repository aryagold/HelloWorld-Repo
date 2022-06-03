package za.co.vzap.Customer.Service;

import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Customer.Repository.CustomerRepository;
import za.co.vzap.Customer.Repository.ReviewRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.ServiceBase;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.User.Model.User;

public class CustomerService extends ServiceBase {
    private IRepository reviewRepository = null;
    private IRepository customerRepository = null;
    
    public CustomerService() {
        reviewRepository = new ReviewRepository();
        customerRepository = new CustomerRepository();
    }
    
    @Override
    public boolean addReview(String comment, int rating, String branchId) {
        Review review = new Review(comment, rating, branchId);
        
        boolean success = reviewRepository.add(review);
        
        if(!success) {
            System.out.println("Review failed");
        }
        
        return success;
    }

    
    @Override
    public boolean addCustomer(String email, String phoneNumber) {
        Customer customer = new Customer(email, phoneNumber);
        
        boolean success = customerRepository.add(customer);
        
        if(!success) {
            System.out.println("Customer add failed");
        }
        
        // code to send the customer an email notifying them they have joined the newsletter.
        // possibly substring the email to get a name from the customer and greet them with it in the email? 
        // as a little something special frill vibe?
        
        return success;
    }
    
    // not related to Customer Service
    
    @Override
    public boolean updateToTeller(String userId) {
        return false;
    }

    @Override
    public boolean addBranch(Branch branch) {
        return false;
    }

    @Override
    public User login(String userId, String password) {
        return null;
    }

    @Override
    public boolean addInventoryControl(InventoryControl inventoryControl, String barcode, String branchId, String size, int quantity) {
        return false;
    }

    @Override
    public boolean addToSale(String productId, int quantity, String userId, String email, int paymentId, String branchId) {
        return false;
    }

    @Override
    public boolean addRefund(String barcode, int quantity) {
       return false;
    }

    @Override
    public void confirmSale(Sale sale) {
       
    }

    @Override
    public List<Inventory> findProduct(String productId) {
        return null;
    }

    @Override
    public boolean deleteSaleLineItem(Product product) {
        return false;
    }

    @Override
    public boolean updateToReserved(String saleId) {
        return false;
    }

    @Override
    public void requestIBT(IEntity iEntity) {
        
    }

    @Override
    public void acceptIBT(int Id) {
       
    }

    @Override
    public void IBTReceived(int Id) {
        
    }

    @Override
    public void viewCustomerReport() {
        
    }

    @Override
    public void viewMonthlySales() {
        
    }

    @Override
    public void viewTopEmployees() {
        
    }

    @Override
    public void viewTop40() {
        
    }

    @Override
    public void viewLeastPerforming() {
        
    }

    @Override
    public void viewProductReport(IEntity iEntity) {
       
    }

    @Override
    public void viewDailySales(IEntity iEntity) {
        
    }

    @Override
    public void downloadCurrentReport() {
        
    }

    @Override
    public boolean addProduct(String arg0, String arg1, String arg3, double arg2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelSale(Sale arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void email(IEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
