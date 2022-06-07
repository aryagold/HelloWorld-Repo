package za.co.vzap.Interface.Service;

import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleLineItemDto;

public interface IPOSService {
    String addSale(Sale sale);
    
    boolean voidSale(Sale sale);
    
    SaleLineItemDto addSaleLineItem(SaleLineItemDto dto) throws Exception;
    
    int addRefund(Refund refund);//here
    
    int addRefundItem(RefundItem refundItem, int refundId);
    
    int addReserved(String barcode);
    
    boolean confirmSale(Sale sale);
    
    boolean deleteSaleLineItem(SaleLineItem saleLineItem);//here
    
    boolean updateToReserved(String saleID);//here
    
    void email(IEntity entity);
}
