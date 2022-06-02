package za.co.vzap.Interface.Service;

import za.co.vzap.Interface.Service.IService;
import java.util.List;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Sale.Model.IEntity;

public abstract class ServiceBase implements IService {
    
    @Override
    public abstract boolean addCustomer(String email, String phoneNumber);

    @Override
    public abstract boolean addReview(String comment, int rating, String branchId);

    @Override
    public abstract void updateToTeller(String userId);

    @Override
    public abstract void addBranch(IEntity iEntity);

    @Override
    public abstract IEntity login(String userId, String password);

    @Override
    public abstract boolean addInventoryControl(InventoryControl inventoryControl, String barcode, String branchId, String size, int quantity);
    
    @Override
    public abstract boolean addProduct(String productId, String name, String categoryId, double price);

    @Override
    public abstract void addToSale(IEntity iEntity);

    @Override
    public abstract void addRefund(IEntity iEntity);

    @Override
    public abstract void confirmSale(IEntity iEntity);

    @Override
    public abstract List<Inventory> findProduct(String productId);

    @Override
    public abstract void deleteSaleLineItem(IEntity iEntity);

    @Override
    public abstract void updateToReserved(IEntity iEntity);

    @Override
    public abstract void requestIBT(IEntity iEntity);

    @Override
    public abstract void acceptIBT(int Id);

    @Override
    public abstract void IBTReceived(int Id);

    @Override
    public abstract void viewCustomerReport();

    @Override
    public abstract void viewMonthlySales();

    @Override
    public abstract void viewTopEmployees();

    @Override
    public abstract void viewTop40();

    @Override
    public abstract void viewLeastPerforming();

    @Override
    public abstract void viewProductReport(IEntity iEntity);

    @Override
    public abstract void viewDailySales(IEntity iEntity);

    @Override
    public abstract void downloadCurrentReport();
}
