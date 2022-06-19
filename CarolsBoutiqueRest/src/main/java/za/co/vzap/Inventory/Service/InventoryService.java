package za.co.vzap.Inventory.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.InventoryControlDto;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.User.Model.User;

public class InventoryService implements IInventoryService {
    private IRepository productRepository = null;
    private IRepository inventoryControlRepository = null;
    private IRepository inventoryRepository = null;
    private IRepository sizeRepository = null;
    private IRepository branchRepository = null;
    private IRepository userRepository = null;
    
    public InventoryService(IRepository productRepository, IRepository inventoryControlRepository, IRepository inventoryRepository, IRepository sizeRepository, IRepository branchRepository, IRepository userRepository) {
        this.productRepository = productRepository;
        this.inventoryControlRepository = inventoryControlRepository;
        this.inventoryRepository = inventoryRepository;
        this.sizeRepository = sizeRepository;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public List<InventoryDto> getBranchInventory(String userId) {
        List<InventoryDto> dtos = new ArrayList<>();
        
        User user = (User) userRepository.getById(userId);
        
        List<Inventory> inventoryItems = inventoryRepository.getWhere("branchId", user.getBranchId());
        
        for(Inventory item : inventoryItems) {
            dtos.add(InventoryMapper.toInventoryDto(item));
        }
        
        return dtos;
    }
    
    @Override
    public InventoryControlDto captureInventory(String userId, String barcode, int quantity) throws Exception {
        InventoryControlDto dto = new InventoryControlDto();
        dto.userId = userId;
        dto.incomingQuantity = quantity;
        
        LocalDateTime date = LocalDateTime.now();
        Timestamp timestampDate = Timestamp.valueOf(date);
        
        List<Inventory> items = inventoryRepository.getWhere("barcode", barcode);
        
        if(items.isEmpty()) {
            throw new Exception("Could not find inventory entry for this barcode.");
        }
        
        User user = (User) userRepository.getById(userId);
        
        Inventory branchInventory = null;
        
        for(Inventory item : items) {
            if(item.getBranchId().equals(user.getBranchId())) branchInventory = item;
        }
        
        if(branchInventory == null) {
            branchInventory = new Inventory();
            
            branchInventory.setBranchId(user.getBranchId());
            branchInventory.setSizeId(items.get(0).getSizeId());
            branchInventory.setProductId(items.get(0).getProductId());
            branchInventory.setBarcode(items.get(0).getBarcode());
            
            inventoryRepository.add(branchInventory);
        }
        
        InventoryControl inventoryControl = new InventoryControl();
        
        inventoryControl.setUserId(userId);
        inventoryControl.setQuantityBefore(branchInventory.getQuantity());
        inventoryControl.setIncomingQuantity(quantity);
        inventoryControl.setNewStockQuantity(branchInventory.getQuantity() + quantity);
        inventoryControl.setDate(timestampDate);
        inventoryControl.setInventoryId(branchInventory.Id);
        
        inventoryControlRepository.add(inventoryControl);
        
        branchInventory.setQuantity(inventoryControl.getNewStockQuantity());
        
        inventoryRepository.update(branchInventory);
            
        inventoryControl.setPosted(true);
            
        inventoryControlRepository.update(inventoryControl);
            
        return InventoryMapper.toInventoryControlDto(inventoryControl);
    }
    
    @Override
    public InventoryDto addInventory(String userId, String productId, int sizeId, String barcode) {
        Inventory inventory = new Inventory();
        
        User user = (User) userRepository.getById(userId);
        
        inventory.setBranchId(user.getBranchId());
        inventory.setBarcode(barcode);
        inventory.setProductId(productId);
        inventory.setQuantity(0);
        inventory.setSizeId(sizeId);
        
        inventoryRepository.add(inventory);
        
        return InventoryMapper.toInventoryDto(inventory);
    }

    @Override
    public List<InventoryDto> findStockWithProductId(String productId) throws Exception {
        List<InventoryDto> dtos = new ArrayList<>();
        List<Inventory> items = inventoryRepository.getWhere("productId", productId);
        
        for(Inventory item: items) {
            InventoryDto dto = InventoryMapper.toInventoryDto(item);
            
            dtos.add(dto);
        }
        
        return dtos;
        
    }
    
    @Override
    public List<InventoryDto> findStockWithBarcode(String barcode) throws Exception {
        List<InventoryDto> dtos = new ArrayList<>();
        
        List<Inventory> inventoryItem = inventoryRepository.getWhere("barcode", barcode);
        String productId = inventoryItem.get(0).getProductId();
        
        List<Inventory> items = inventoryRepository.getWhere("productId", productId);
        
        for(Inventory item : items) {
            InventoryDto dto = InventoryMapper.toInventoryDto(item);
            
            dtos.add(dto);
        }
        
        return dtos;
    }
    
    @Override
    public InventoryDto getItem(String barcode) {
        List<Inventory> items = inventoryRepository.getWhere("barcode", barcode);
        
        InventoryDto dto = InventoryMapper.toInventoryDto(items.get(0));
        
        return dto;
    }  

    @Override
    public List<InventoryDto> getLowStockQuantity(int threshold) {
         List<Inventory> items = inventoryRepository.getAll();
         List<InventoryDto> dtos = new ArrayList<>();
         
         for(Inventory item : items) {
             if(item.getQuantity() > threshold) continue;
             
             dtos.add(InventoryMapper.toInventoryDto(item));
         }
         
         return dtos;
    }

}
