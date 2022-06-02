package za.co.vzap.User.Service;

import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.POS.Service.ServiceBase;
import za.co.vzap.Sale.Model.IEntity;
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
    public boolean updateToTeller(String userId) {
        User user = repository.getById(userId);
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
    public boolean addInventory(int quantity, int previousQuantity) {
        return false;
    }
    
    @Override
    public void addCustomer(String name, String email, String phoneNumber) {

    }

    @Override
    public void addReview(String comment, int Rating) {

    }

    @Override
    public boolean addToSale(IEntity IEntity) {
        return false;
    }

    @Override
    public boolean addRefund(IEntity IEntity) {
        return false;
    }

    @Override
    public boolean confirmSale(IEntity IEntity) {
        return false;
    }

    @Override
    public List<IEntity> findProduct(String productId) {
        return null;
    }

    @Override
    public boolean deleteSaleLineItem(IEntity IEntity) {
        return false;
    }

    @Override
    public boolean updateToReserved(IEntity IEntity) {
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
}
