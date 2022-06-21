package za.co.vzap.Admin.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Communication.Email.Email;
import za.co.vzap.Communication.Model.CommunicationDto;
import za.co.vzap.Communication.Model.EmailTypeEnum;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.POS.Model.SaleDto;

public class ReservedChecker extends Thread {
    IPOSService posService;
    
    public ReservedChecker(IPOSService posService) {
        this.posService = posService;
    }

    @Override
    public void run() {
        while(true) {
            List<SaleDto> sales = posService.getReserved();
            
            for(SaleDto sale : sales) {
                long diff = Timestamp.valueOf(LocalDateTime.now()).getTime() - sale.date.getTime();
                
                double hours = (double) diff / (60 * 60 * 1000);
                
                if(hours > 11.5 && hours < 12.5) {
                    emailReminder(sale);
                } 
                
                if(hours > 24) {
                    cancelSale(sale);
                }
            }
            
            try {
                Thread.sleep(1000 * 60 * 60 * 2);
            } catch (InterruptedException ex) {
                Logger.getLogger(InventoryChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void emailReminder(SaleDto sale) {
        CommunicationDto dto = new CommunicationDto();

        dto.emailType = EmailTypeEnum.RESERVENOTIFICATION;
        dto.emailAddressTo = sale.customerEmail;
        dto.data = sale;

        new Email(dto).start();
    }
    
    private void cancelSale(SaleDto sale) {
    
    }
}
