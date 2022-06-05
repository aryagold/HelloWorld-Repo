package za.co.vzap.Inventory.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.ProductCategory;
import za.co.vzap.Inventory.Model.ProductCode;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductCategoryRepository;
import za.co.vzap.Inventory.Repository.ProductCodeRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.ProductSaleRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IBTStatusEnum;
import za.co.vzap.Sale.Model.ProductSale;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.SaleRepository;

public class InventoryService implements IInventoryService {
    private IRepository productRepository = null;
    private IRepository productCategoryRepository = null;
    private IRepository inventoryControlRepository = null;
    private IRepository inventoryRepository = null;
    private IRepository sizeRepository = null;
    private IRepository productCodeRepository = null;
    private IRepository ibtRepository = null;
    private IRepository saleRepository = null;
    private IRepository productSaleRepository = null;
    
    public InventoryService(IRepository productRepository, IRepository productCategoryRepository, IRepository inventoryControlRepository, IRepository inventoryRepository, IRepository sizeRepository, IRepository productCodeRepository, IRepository ibtRepository, IRepository saleRepository, IRepository productSaleRepository) {
        productRepository = new ProductRepository();
        productCategoryRepository = new ProductCategoryRepository();
        inventoryControlRepository = new InventoryControlRepository();
        inventoryRepository = new InventoryRepository();
        sizeRepository = new SizeRepository();
        productCodeRepository = new ProductCodeRepository();
        ibtRepository = new IBTRepository();
        saleRepository = new SaleRepository();
        productSaleRepository = new ProductSaleRepository();
    }
    
    
    @Override
    public int addInventoryControl(InventoryControl inventoryControl, Inventory inventory) {
        LocalDateTime date = LocalDateTime.now();
        Timestamp timestampDate = Timestamp.valueOf(date);
        inventoryControl.setDate(timestampDate);
        
        List<Inventory> items = inventoryRepository.getWhere("barcode", inventory.getBarcode());
        
        String productId = inventoryControl.getProductId();
        
        List<ProductCode> codes = productCodeRepository.getWhere("productId", productId);
        
        int incomingQuantity = inventoryControl.getIncomingQuantity();
        
        int quantityBefore = 0;
        
        for(Inventory i : items) {
            quantityBefore = i.getQuantity();
            inventoryControl.setQuantityBefore(quantityBefore);
        }
        
        int newQuantity = quantityBefore + incomingQuantity;
        inventoryControl.setNewStockQuantity(newQuantity);
        
        int id = inventoryControlRepository.add(inventoryControl);
            
        addInventory(inventory);
            
        inventoryControl.setPosted(true);
            
        inventoryControlRepository.update(inventoryControl);
            
        return inventoryControl.Id;
        
    }
    
    private String addInventory(Inventory inventory) {
        inventoryRepository.add(inventory);
        
        System.out.println("Product not inventoried");
       
        return inventory.getBarcode();
    }

    @Override
    public List<Inventory> findProduct(String productId) {
        List<Inventory> items = inventoryRepository.getWhere("productCode", productId.substring(3)); // not sure if number e.g 001 = 1 ?
        
        if(items.isEmpty()) {
            System.out.println("Could not find any products.");
        }
        
        return items;
        
    }
    
    @Override
    public void requestIBT(IBT ibt) {
        int id = ibtRepository.add(ibt);
//        new Email(3,).start();
        // notify other store of the request
    }

    @Override
    public void acceptIBT(IBT ibt) {
        ibt.setStatus(IBTStatusEnum.APPROVED);
        boolean success = ibtRepository.update(ibt);
        
        // thread to start ibt and email the manager after the ibt thread is done to say it has been delivered
    }
    
    @Override
    public void declineIBT(IBT ibt) {
        ibt.setStatus(IBTStatusEnum.REJECTED);
        ibtRepository.update(ibt);
        
        // notify other store it has been rejected
    }

    @Override
    public void IBTReceived(IBT ibt) {
        ibt.setStatus(IBTStatusEnum.COMPLETED);
        ibtRepository.update(ibt);
        
        // message must be sent to customer's phone to notfy them their product has arrived
    }
    
    @Override
    public void payIBT(IBT ibt, Sale sale) {
        LocalDateTime date = LocalDateTime.now();
        saleRepository.add(new Sale(sale.getUserId(), sale.getEmail(), Timestamp.valueOf(date), sale.getPaymentId(), ibt.getBranchIdFrom(), SaleStatusEnum.COMPLETED));
        productSaleRepository.add(new ProductSale(ibt.getProductId(), sale.saleId));
        
    }
    
    @Override
    public String addProduct(String productId, String name, String categoryId, double price) {
        Product product = new Product(productId, name, price);
        
        String id = productRepository.add2(product);
        
        productCategoryRepository.add(new ProductCategory(productId, categoryId));
        
        productCodeRepository.add(productId);
        
        return id;
    }
        
}
