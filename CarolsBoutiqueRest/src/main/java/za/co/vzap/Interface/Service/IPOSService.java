package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.RefundItemDto;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleLineItemDto;

public interface IPOSService {
    String addSale(Sale sale);
    
    boolean voidSale(String saleId);
    
    SaleLineItemDto addSaleLineItem(SaleLineItemDto dto) throws Exception;
    
    List<SaleLineItemDto> getSaleLineItems(String saleId);
    
    int addRefund(Refund refund);
    
    RefundItemDto addRefundItem(RefundItemDto dto);
    
    boolean completeSale(Payment payment, String saleId);
    
    boolean deleteSaleLineItem(int saleLineItemId);
    
    boolean reserveSale(String saleID);
    
    void requestIBT(InventoryDto dto, String phoneNumber, String branchIdTo);

    void acceptIBT(int id);

    void IBTReceived(int id);

    void declineIBT(int id);

    void payIBT(List<InventoryDto> dtos, int id, String email, String cardNumber);
}
