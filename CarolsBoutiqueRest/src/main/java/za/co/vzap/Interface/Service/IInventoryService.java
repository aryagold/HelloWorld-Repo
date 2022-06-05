
package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.Sale;

public interface IInventoryService {
    int addInventoryControl(InventoryControl inventoryControl, Inventory inventory);//here
    
    String addProduct(String productId, String name, String categoryId, double price);
    
    List<Inventory> findProduct(String productId);
    
    void requestIBT(IBT ibt);
    
    void acceptIBT(IBT ibt);
    
    void IBTReceived(IBT ibt);
    
    void declineIBT(IBT ibt);
    
    void payIBT(IBT ibt, Sale sale);
}
