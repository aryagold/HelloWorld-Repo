
package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Model.Inventory.InventoryControlDto;
import za.co.vzap.Model.Inventory.InventoryDto;

public interface IInventoryService {
    List<InventoryDto> getBranchInventory(String userId);
    
    InventoryControlDto captureInventory(String userId, String barcode, int quantity) throws Exception;
    
    InventoryDto addInventory(String userId, String productId, int sizeId, String barcode);
    
    List<InventoryDto> findStockWithProductId(String productId) throws Exception;
    
    List<InventoryDto> findStockWithBarcode(String barcode) throws Exception;
    
}
