package za.co.vzap.User.Service;

import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.ServiceBase;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

public class UserService extends ServiceBase {

    private IRepository<User> repository;
    private IRepository<Branch> repository1;

    public UserService() {
        this.repository = new UserRepository();
        this.repository1 = new BranchRepository();
    }
   

    //needs implementation
    @Override
    public boolean updateToTeller(String userId) { // this method reads in a user and resets their role but does not update their role in the
        User user = repository.getById(userId);    // database. please use update method for user repository
        user.setRole(RoleEnum.ofStatusCode(1));
        if (user.getRole().getValue() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addBranch(Branch branch) {
        if (repository1.add(branch)){
            return true;
        }
        return false;
    }

    @Override
    public User login(String userId, String password) {
        User user = repository.getById(userId);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    //not related to UserService
    @Override 
    public boolean addInventoryControl(InventoryControl inventoryControl, String barcode, String branchId, String size, int quantity) {
        return false;
    }
    
    @Override
    public boolean addCustomer(String email, String phoneNumber) {
        return false;
    }

    @Override
    public boolean addReview(String comment, int Rating, String branchId) {
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
    public boolean updateToReserved(String saleID) {
        return false;
    }

    @Override
    public void requestIBT(IEntity IEntity) {

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
    public void viewProductReport(IEntity IEntity) {

    }

    @Override
    public void viewDailySales(IEntity IEntity) {

    }

    @Override
    public void downloadCurrentReport() {

    }

    @Override
    public boolean addProduct(String arg0, String arg1, String arg2, double arg3) {
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
