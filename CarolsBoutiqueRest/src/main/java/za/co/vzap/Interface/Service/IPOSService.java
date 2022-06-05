package za.co.vzap.Interface.Service;

import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Sale;

public interface IPOSService {
    int addToSale(String productId, int quantity, String userId, String email, int paymentId, String branchId);
    
    int addRefund(String barcode, int quantity);//here
    
    String confirmSale(Sale sale);
    
    void cancelSale(Sale sale);
    
    boolean deleteSaleLineItem(Product product);//here
    
    boolean updateToReserved(String saleID);//here
    
    void email(IEntity entity);
}
