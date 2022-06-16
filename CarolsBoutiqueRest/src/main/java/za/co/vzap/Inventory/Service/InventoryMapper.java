package za.co.vzap.Inventory.Service;

import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.InventoryControlDto;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;

public class InventoryMapper {

    private static IRepository productRepository = new ProductRepository();
    private static IRepository branchRepository = new BranchRepository();
    private static IRepository sizeRepository = new SizeRepository();
    private static IRepository inventoryRepository = new InventoryRepository();
    
    
    public static InventoryDto toInventoryDto(Inventory inventory) {
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
        dto.price = product.getPrice();

        Size size = (Size) sizeRepository.getById(inventory.getSizeId());
        dto.sizeName = size.getSize();

        return dto;
    }

    public static InventoryControlDto toInventoryControlDto(InventoryControl inventoryControl) {
        InventoryControlDto dto = new InventoryControlDto();

        dto.Id = inventoryControl.Id;
        dto.userId = inventoryControl.getUserId();
        dto.inventoryId = inventoryControl.getInventoryId();
        dto.date = inventoryControl.getDate();
        dto.quantityBefore = inventoryControl.getQuantityBefore();
        dto.incomingQuantity = inventoryControl.getIncomingQuantity();
        dto.newStockQuantity = inventoryControl.getNewStockQuantity();
        dto.posted = inventoryControl.isPosted();

        Inventory inventory = (Inventory) inventoryRepository.getById(dto.inventoryId);

        Product product = (Product) productRepository.getById(inventory.getProductId());
        dto.productName = product.getName();

        Size size = (Size) sizeRepository.getById(inventory.getSizeId());
        dto.sizeName = size.getSize();

        return dto;
    }
}
