
package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Inventory.Model.InventoryControlDto;
import za.co.vzap.Inventory.Model.InventoryDto;

public interface IInventoryService {
    List<InventoryDto> getBranchInventory(String userId);
    
    InventoryControlDto captureInventory(String userId, String barcode, int quantity) throws Exception;
    
    InventoryDto addInventory(String userId, String productId, int sizeId, String barcode);
    
    List<InventoryDto> findStockWithProductId(String productId) throws Exception;
    
    List<InventoryDto> findStockWithBarcode(String barcode) throws Exception;
    
    InventoryDto getItem(String barcode);
    
    List<InventoryDto> getLowStockQuantity(int threshold); // this is not a remotely accessed method. do not place in servlets.
    
}
