package za.co.vzap.Admin.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Communication.Email.Email;
import za.co.vzap.Communication.Model.CommunicationDto;
import za.co.vzap.Communication.Model.EmailTypeEnum;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Inventory.Service.InventoryService;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

public class InventoryChecker extends Thread {
    IInventoryService inventoryService;
    IRepository userRepository;
    
    private int interval;
    private int threshold;
    
    public InventoryChecker(IInventoryService inventoryService, IRepository userRepository, int interval, int threshold) {
        this.inventoryService = inventoryService;
        this.userRepository = userRepository;
       
        this.interval = interval;
        this.threshold = threshold;
    }

    @Override
    public void run() {
        while(true) {
            List<InventoryDto> inventoryItems = inventoryService.getLowStockQuantity(threshold);
        
            for(InventoryDto item : inventoryItems) {
                List<User> users = userRepository.getWhere("branchId", item.branchId);
                
                for(User user : users) {
                    if(user.getRole() != RoleEnum.MANAGER) continue;
                    
                    CommunicationDto dto = new CommunicationDto();
                    
                    dto.emailType = EmailTypeEnum.DEPLETEDSTOCK;
                    dto.emailAddressTo = user.getEmail();
                    dto.data = item;
                    
                    new Email(dto).start();               
                }
            }
            
            try {
                Thread.sleep(interval*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(InventoryChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
}
