package za.co.vzap.Admin.Service;

import java.util.Scanner;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Inventory.Service.InventoryService;
import za.co.vzap.User.Repository.UserRepository;

public class AdminService {
    
    public static void main(String[] args) {
        IRepository userRepository = new UserRepository();
        IRepository productRepository = new ProductRepository();
        IRepository inventoryControlRepository = new InventoryControlRepository();
        IRepository inventoryRepository = new InventoryRepository();
        IRepository sizeRepository = new SizeRepository();
        IRepository branchRepository = new BranchRepository();
        
        IInventoryService inventoryService = new InventoryService(productRepository, inventoryControlRepository, inventoryRepository, sizeRepository, branchRepository, userRepository);
        
        new InventoryChecker(inventoryService, userRepository, 10, 3).start();
     
        while(true) {
            Scanner input = new Scanner(System.in);
            
            String in = input.nextLine();
            
            if(in.equals("q")) {
                
            }
        }
    }
    
    
}
