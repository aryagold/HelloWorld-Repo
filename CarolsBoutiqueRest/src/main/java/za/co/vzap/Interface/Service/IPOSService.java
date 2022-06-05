package za.co.vzap.Interface.Service;

import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Sale;

public interface IPOSService {
    int addToSale(String saleId, String barcode);
    
    int addRefund(String barcode);//here
    
    int addReserved(String barcode);
    
    String confirmSale(Sale sale);
    
    void cancelSale(Sale sale);
    
    boolean deleteSaleLineItem(Product product);//here
    
    boolean updateToReserved(String saleID);//here
    
    void email(IEntity entity);
}
