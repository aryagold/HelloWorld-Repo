
package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.Sale;

public interface IInventoryService {
    InventoryDto addInventoryControl(InventoryControl inventoryControl, InventoryDto dto);
    
    String addInventory(InventoryDto dto);
    
    String addProduct(Product product, List<String> categoryIds);
    
    String addCategory(Category category);
    
    List<InventoryDto> findProductWithProductId(String productId) throws Exception;
    
    List<InventoryDto> findProductWithBarcode(String barcode) throws Exception;
    
    void requestIBT(IBT ibt);
    
    void acceptIBT(IBT ibt);
    
    void IBTReceived(IBT ibt);
    
    void declineIBT(IBT ibt);
    
    void payIBT(IBT ibt, Sale sale);
}
