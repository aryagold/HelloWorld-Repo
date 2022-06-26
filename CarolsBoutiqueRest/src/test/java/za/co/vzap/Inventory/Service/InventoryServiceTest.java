/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Service;


import java.nio.file.attribute.UserDefinedFileAttributeView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.InventoryControlDto;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductCategoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Sale.Repository.SaleRepository;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;


/**
 *
 * @author macpe
 */
public class InventoryServiceTest {//complete
    
    private IInventoryService inventoryService;
    private IRepository productDB;
    private IRepository productCategoryDB;
    private IRepository inventoryControlDB;
    private IRepository inventoryDB;
    private IRepository sizeDB;
    private IRepository saleDB;
    private IRepository categoryDB;
    private IRepository branchDB;
    private IRepository userDB;
   
    @Test
    public void testAddInventory() {//done
        
        productDB = new ProductRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        branchDB = new BranchRepository();
        userDB = new UserRepository();
        categoryDB = new CategoryRepository();

        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, branchDB, userDB, categoryDB);
        
        InventoryDto result = inventoryService.addInventory("US001","PR033",2,"00332");
        
        assertEquals( InventoryDto.class , result.getClass());
        
    }
    
    @Test
    public void testGetBranchInventory(){//done
        
        productDB = new ProductRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        branchDB = new BranchRepository();
        userDB = new UserRepository();
        categoryDB = new CategoryRepository();

        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, branchDB, userDB, categoryDB);

        Branch branch = new Branch("TestBranch", 10000, 1000);
        String branchID = branchDB.add2(branch);
        User user = new User("TestName", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        
        List<InventoryDto> result = inventoryService.getBranchInventory(userID);

        assertEquals( ArrayList.class , result.getClass() );
        
    }
    
    @Test
    public void testCaptureInventory(){//done
        
        productDB = new ProductRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        branchDB = new BranchRepository();
        userDB = new UserRepository();
        categoryDB = new CategoryRepository();

        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, branchDB, userDB, categoryDB);

        InventoryControlDto result = null;
        
        try {
            
           result = inventoryService.captureInventory("US001", "00332" , 10);
        
        } catch (Exception ex) {
            Logger.getLogger(InventoryServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals( InventoryControlDto.class , result.getClass());
        
    }
    
    @Test
    public void testFindInventory() throws Exception{//done
        
        productDB = new ProductRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        branchDB = new BranchRepository();
        userDB = new UserRepository();
        categoryDB = new CategoryRepository();

        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, branchDB, userDB, categoryDB);

        List<InventoryDto> result = inventoryService.findInventory("00332");
        
        assertEquals( ArrayList.class , result.getClass());
        
    }
    
    @Test
    public void testGetItem(){//done
        
        productDB = new ProductRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        branchDB = new BranchRepository();
        userDB = new UserRepository();
        categoryDB = new CategoryRepository();

        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, branchDB, userDB, categoryDB);

        InventoryDto result = inventoryService.getItem("00332");
        
        assertEquals( InventoryDto.class , result.getClass());
        
    }
    
    @Test
    public void testGetLowStockQuantity(){//done
        
        productDB = new ProductRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        branchDB = new BranchRepository();
        userDB = new UserRepository();
        categoryDB = new CategoryRepository();

        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, branchDB, userDB, categoryDB);

        List<InventoryDto> result = inventoryService.getLowStockQuantity(3);

        assertEquals( ArrayList.class , result.getClass());
        
    }
    
}
