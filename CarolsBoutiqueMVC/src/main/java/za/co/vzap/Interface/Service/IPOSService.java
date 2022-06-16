package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Model.Inventory.InventoryDto;
import za.co.vzap.Model.Sale.IbtDto;
import za.co.vzap.Model.Sale.Payment;
import za.co.vzap.Model.Sale.Refund;
import za.co.vzap.Model.Sale.RefundDto;
import za.co.vzap.Model.Sale.RefundItemDto;
import za.co.vzap.Model.Sale.Sale;
import za.co.vzap.Model.Sale.SaleDto;
import za.co.vzap.Model.Sale.SaleLineItemDto;

public interface IPOSService {
    String addSale(Sale sale);
    
    SaleDto voidSale(String saleId);
    
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
