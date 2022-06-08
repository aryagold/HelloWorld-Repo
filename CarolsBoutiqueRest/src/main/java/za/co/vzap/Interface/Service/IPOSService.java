package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.RefundItemDto;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleLineItemDto;

public interface IPOSService {
    String addSale(Sale sale);
    
    boolean voidSale(Sale sale);
    
    SaleLineItemDto addSaleLineItem(SaleLineItemDto dto) throws Exception;
    
    List<SaleLineItemDto> getSaleLineItems(String saleId);
    
    int addRefund(Refund refund);//here
    
    RefundItemDto addRefundItem(RefundItemDto dto);
    
    boolean completeSale(Sale sale);
    
    boolean deleteSaleLineItem(int saleLineItemId);//here
    
    boolean reserveSale(String saleID);//here
    
    void email(IEntity entity);
}
