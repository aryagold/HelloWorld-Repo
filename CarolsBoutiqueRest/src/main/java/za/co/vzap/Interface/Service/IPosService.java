package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.POS.Model.IbtDto;
import za.co.vzap.POS.Model.Refund;
import za.co.vzap.POS.Model.RefundDto;
import za.co.vzap.POS.Model.RefundItemDto;
import za.co.vzap.POS.Model.SaleDto;

public interface IPosService {
    SaleDto addSale(SaleDto dto);
    
    SaleDto getSale(String id);
    
    int addRefund(Refund refund);
    
    RefundItemDto addRefundItem(RefundItemDto dto);
    
    RefundDto completeRefund(int refundId);
    
    boolean reserveSale(String saleID);
    
    IbtDto addIbt(IbtDto dto);
    
    IbtDto updateIbt(IbtDto dto);

    IbtDto approveIbt(int id);

    IbtDto declineIbt(int id);
    
    IbtDto ibtReceived(int id);
    
    List<IbtDto> listIbt(String userId, int type);
    
   
}
