package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Sale.Model.IbtDto;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItemDto;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItemDto;

public interface IPOSService {
    String addSale(Sale sale);
    
    boolean voidSale(String saleId);
    
    SaleLineItemDto addSaleLineItem(SaleLineItemDto dto) throws Exception;
    
    List<SaleLineItemDto> getSaleLineItems(String saleId);
    
    int addRefund(Refund refund);
    
    RefundItemDto addRefundItem(RefundItemDto dto);
    
    int completeSale(Payment payment, String saleId);
    
    boolean deleteSaleLineItem(int saleLineItemId);
    
    boolean reserveSale(String saleID);
    
    IbtDto requestIbt(int inventoryIdFrom, String branchIdTo, int quantity, String phoneNumber, String emailAddress);

    IbtDto approveIbt(int id);

    IbtDto declineIbt(int id);
    
    IbtDto ibtReceived(int id);

    void payIBT(InventoryDto dto, int id, Sale sale, Payment payment);
    
    List<IbtDto> viewIbt();
}
