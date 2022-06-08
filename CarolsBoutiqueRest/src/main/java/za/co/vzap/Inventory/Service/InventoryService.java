package za.co.vzap.Inventory.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.ProductCategory;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductCategoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IBTStatusEnum;
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
    private IRepository ibtRepository = null;
    private IRepository saleRepository = null;
    private IRepository categoryRepository = null;
    private IRepository branchRepository = null;
    
    public InventoryService(IRepository productRepository, IRepository productCategoryRepository, IRepository inventoryControlRepository, IRepository inventoryRepository, IRepository sizeRepository, IRepository ibtRepository, IRepository saleRepository, IRepository categoryRepository, IRepository branchRepository) {
        this.productRepository = new ProductRepository();
        this.productCategoryRepository = new ProductCategoryRepository();
        this.inventoryControlRepository = new InventoryControlRepository();
        this.inventoryRepository = new InventoryRepository();
        this.sizeRepository = new SizeRepository();
        this.ibtRepository = new IBTRepository();
        this.saleRepository = new SaleRepository();
        this.categoryRepository = new CategoryRepository();
        this.branchRepository = new BranchRepository();
    }
    
    @Override
    public String addProduct(Product product, List<String> categoryIds) {
        String id = productRepository.add2(product);
        
        for(String catId : categoryIds) {
            productCategoryRepository.add(new ProductCategory(product.getProductId(), catId));
        }
        
        return id + " has been added to the product catalogue.";
    }
    
    @Override
    public String addCategory(Category category) {
        String id = categoryRepository.add2(category);
        
        
        return id + " has been added to the category catalogue.";
    }
    
    @Override
    public InventoryDto addInventoryControl(String userId, String barcode, int quantity) throws Exception {
        LocalDateTime date = LocalDateTime.now();
        Timestamp timestampDate = Timestamp.valueOf(date);
        
        List<Inventory> items = inventoryRepository.getWhere("barcode", barcode);
        
        if(items.isEmpty()) {
            throw new Exception("Could not find product.");
        }

        Inventory inventory = items.get(0);
        
        InventoryControl inventoryControl = new InventoryControl();
        
        inventoryControl.setUserId(userId);
        inventoryControl.setQuantityBefore(inventory.getQuantity());
        inventoryControl.setIncomingQuantity(quantity);
        inventoryControl.setNewStockQuantity(inventory.getQuantity() + quantity);
        inventoryControl.setDate(timestampDate);
        
        inventoryControlRepository.add(inventoryControl);
        
        inventory.setQuantity(inventoryControl.getNewStockQuantity());
        
        inventoryRepository.update(inventory);
            
        inventoryControl.setPosted(true);
            
        inventoryControlRepository.update(inventoryControl);
            
        return toInventoryDto(inventory);
    }
    
    @Override
    public String addInventory(InventoryDto dto) {
        List<Size> sizes = sizeRepository.getWhere("size", dto.sizeName);
        int sizeId = sizes.get(0).Id;
        
        List<Branch> branches = branchRepository.getWhere("name", dto.branchName);
        
        String branchId = branches.get(0).branchId;
        
        Inventory inventory = new Inventory(branchId, sizeId, dto.productId, dto.barcode, dto.quantity);
        inventoryRepository.add(inventory);
       
        return dto.barcode;
    }

    @Override
    public List<InventoryDto> findProductWithProductId(String productId) throws Exception {
        List<Inventory> items = inventoryRepository.getWhere("productId", productId);
        List<InventoryDto> formattedItems = new ArrayList<>();
        
        if(items.isEmpty()) {
            throw new Exception("Could not find any products matching ID");
        }
        
        for(Inventory item: items) {
            
        }
        
        return formattedItems;
        
    }
    
    @Override
    public List<InventoryDto> findProductWithBarcode(String barcode) throws Exception {
        List<Inventory> inventoryItem = inventoryRepository.getWhere("barcode", barcode);
        String productId = inventoryItem.get(0).getProductId();
        
        List<Inventory> items = inventoryRepository.getWhere("productId", productId);
        List<InventoryDto> formattedItems = new ArrayList<>();
        
        if(items.isEmpty()) {
            throw new Exception("Could not find any products matching barcode.");
        }
        
        for(Inventory item : items) {
            
        }
        
        return formattedItems;
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
        saleRepository.add(new Sale(sale.getUserId(), sale.getCustomerEmail(), Timestamp.valueOf(date), sale.getPaymentId(), SaleStatusEnum.COMPLETED));
        // add to salelineitemrepository?
        
    }
    
    private InventoryDto toInventoryDto(Inventory inventory) {
        InventoryDto dto = new InventoryDto();
        
        dto.Id = inventory.Id;
        dto.branchId = inventory.getBranchId();
        dto.sizeId = inventory.getSizeId();
        dto.productId = inventory.getProductId();
        dto.barcode = inventory.getBarcode();
        dto.quantity = inventory.getQuantity();
        
        Branch branch = (Branch) branchRepository.getById(inventory.getBranchId());
        dto.branchName = branch.getName();
        
        Product product = (Product) productRepository.getById(inventory.getProductId());
        dto.productName = product.getName();
    
        Size size = (Size) sizeRepository.getById(inventory.getSizeId());
        dto.sizeName = size.getSize();
    
        return dto;
    }

    
        
}
