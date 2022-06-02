package za.co.vzap.Inventory.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.ServiceBase;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.ProductCategory;
import za.co.vzap.Inventory.Model.ProductCode;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductCategoryRepository;
import za.co.vzap.Inventory.Repository.ProductCodeRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Sale.Model.IEntity;

public class InventoryService extends ServiceBase {
    private IRepository productRepository = null;
    private IRepository productCategoryRepository = null;
    private IRepository inventoryControlRepository = null;
    private IRepository inventoryRepository = null;
    private IRepository sizeRepository = null;
    private IRepository productCodeRepository = null;
    
    public InventoryService(IRepository productRepository) {
        productRepository = new ProductRepository();
        productCategoryRepository = new ProductCategoryRepository();
        inventoryControlRepository = new InventoryControlRepository();
        inventoryRepository = new InventoryRepository();
        sizeRepository = new SizeRepository();
        productCodeRepository = new ProductCodeRepository();
    }
    
    
    @Override
    public boolean addInventoryControl(InventoryControl inventoryControl, String barcode, String branchId, String size, int quantity) {
        LocalDateTime date = LocalDateTime.now();
        Timestamp timestampDate = Timestamp.valueOf(date);
        List<Size> sizes = sizeRepository.getWhere("size", size);
        List<Inventory> items = inventoryRepository.getWhere("barcode", barcode);
        
        String productId = inventoryControl.getProductId();
        
        List<ProductCode> codes = productCodeRepository.getWhere("productId", productId);
        
        String userId = inventoryControl.getUserId();
        int incomingQuantity = inventoryControl.getIncomingQuantity();
        
        int sizeId = 0;
        int quantityBefore = 0;
        int productCode = 0;
        
        for(Size s : sizes) {
            sizeId = s.Id;
        }
        
        for(Inventory i : items) {
            quantityBefore = i.getQuantity();
        }
        
        for(ProductCode pc : codes) {
            productCode = pc.Id;
        }
        
        int newQuantity = quantityBefore + incomingQuantity;
        
        boolean success = inventoryControlRepository.add(new InventoryControl(userId, productId, timestampDate, quantityBefore, incomingQuantity, newQuantity, false));
        
        if(success) {
            Inventory inventory = new Inventory(branchId, sizeId, productCode, barcode, quantity);
            
            addInventory(inventory);
            
            inventoryControlRepository.update(new InventoryControl(userId, productId, timestampDate, quantityBefore, incomingQuantity, newQuantity, true));
            
            return true;
            
        } else {
            
            return false;
        }
    }
    
    private boolean addInventory(Inventory inventory) {
        boolean success = inventoryRepository.add(inventory);
        
        if(!success) {
           System.out.println("Product not inventoried");
        } 
        
        return success;
    }

    @Override
    public List<Inventory> findProduct(String productId) {
        return null;
    }
    
    @Override
    public boolean addProduct(String productId, String name, String categoryId, double price) {
        Product product = new Product(productId, name, price);
        
        boolean success = productRepository.add(product);
        
        if(!success) {
            System.out.println("Product not added");
        }
        
        productCategoryRepository.add(new ProductCategory(productId, categoryId));
        
        productCodeRepository.add(productId);
        
        return success;
    }
    

    //Not related to InventoryService


    @Override
    public boolean addCustomer(String email, String phoneNumber) {
        return false;
    }

    @Override
    public boolean addReview(String comment, int rating, String branchId) {
        return false;
    }

    @Override
    public void updateToTeller(String userId) {
        
    }

    @Override
    public void addBranch(IEntity iEntity) {
        
    }

    @Override
    public IEntity login(String userId, String password) {
        return null;
    }

    @Override
    public void addToSale(IEntity iEntity) {
        
    }

    @Override
    public void addRefund(IEntity iEntity) {
        
    }

    @Override
    public void confirmSale(IEntity iEntity) {
      
    }

    @Override
    public void deleteSaleLineItem(IEntity iEntity) {
        
    }

    @Override
    public void updateToReserved(IEntity iEntity) {
        
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

    
}
