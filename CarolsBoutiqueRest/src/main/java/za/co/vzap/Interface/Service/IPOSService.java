package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.POS.Model.IbtDto;
import za.co.vzap.POS.Model.RefundDto;
import za.co.vzap.POS.Model.SaleDto;

public interface IPOSService {
    SaleDto addSale(SaleDto dto);
    
    SaleDto getSale(String id);
    
    RefundDto addRefund(RefundDto dto);
    
    RefundDto getRefund(int id);
    
    IbtDto addIbt(IbtDto dto);
    
    IbtDto updateIbt(IbtDto dto);
    
    void updateIbtStatus(IbtDto dto);
    
    List<IbtDto> listIbt(String userId, int type);
    
    List<SaleDto> getReserved(); // this method does NOT hit an endpoint, no servlet/client methods.
   
}
