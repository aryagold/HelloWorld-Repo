package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Model.Sale.IbtDto;
import za.co.vzap.Model.Sale.RefundDto;
import za.co.vzap.Model.Sale.SaleDto;

public interface IPOSService {

    SaleDto addSale(SaleDto dto);

    SaleDto getSale(String id);

    RefundDto addRefund(RefundDto dto);

    RefundDto getRefund(int id);

    IbtDto addIbt(IbtDto dto);

//    IbtDto updateIbt(IbtDto dto);
    
    void approveIbt(int id);
    
    void declineIbt(int id);
    
    void receivedIbt(int id);

    List<IbtDto> listIbt(String userId, int type);

}
