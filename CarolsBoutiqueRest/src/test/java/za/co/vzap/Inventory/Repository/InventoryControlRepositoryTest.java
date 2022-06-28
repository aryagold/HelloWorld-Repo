/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

/**
 *
 * @author macpe
 */
public class InventoryControlRepositoryTest {//complete
    
    private IRepository inventoryControlRepository ;
    private IRepository userDB;
    private IRepository productDB;
    private IRepository branchDB;
    
    @Test
    public void testAdd() {//works
        
        inventoryControlRepository = new InventoryControlRepository();
        userDB = new UserRepository();
        productDB = new ProductRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch",10000,1000);
        String branchID = branchDB.add2(branch);
        User user = new User("TestUser1","TestUser@gmail.com",branchID,"TestUSerPassword",RoleEnum.GENERAL_EMPLOYEE);
        String userID = userDB.add2(user);
        Product product = new Product("TestProduct1",12000);
        String productID = productDB.add2(product);
       
        InventoryControl invenCtrl = new InventoryControl(userID, 1,Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true);
        
        Integer result = inventoryControlRepository.add(invenCtrl);
        
        assertEquals( Integer.class , result.getClass() );
       
    }

    
    @Test
    public void testUpdate() {//works
        inventoryControlRepository = new InventoryControlRepository();
        userDB = new UserRepository();
        productDB = new ProductRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch",10000,1000);
        String branchID = branchDB.add2(branch);
        User user = new User("TestUser1","TestUser@gmail.com",branchID,"TestUSerPassword",RoleEnum.GENERAL_EMPLOYEE);
        String userID = userDB.add2(user);
        Product product = new Product("TestProduct1",12000);
        String productID = productDB.add2(product);
       
        InventoryControl invenCtrl = new InventoryControl(userID, 1,Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true);
        
        Integer ID = inventoryControlRepository.add(invenCtrl);
        
        invenCtrl.setIncomingQuantity(20);
        
        invenCtrl.Id = ID;
        Boolean result = inventoryControlRepository.update(invenCtrl);
        
        assertEquals( Boolean.class , result.getClass() );
       
    }

    
    @Test
    public void testGetById_int() {//works
        inventoryControlRepository = new InventoryControlRepository();
        userDB = new UserRepository();
        productDB = new ProductRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch",10000,1000);
        String branchID = branchDB.add2(branch);
        User user = new User("TestUser1","TestUser@gmail.com",branchID,"TestUSerPassword",RoleEnum.GENERAL_EMPLOYEE);
        String userID = userDB.add2(user);
        Product product = new Product("TestProduct1",12000);
        String productID = productDB.add2(product);
        
        InventoryControl invenCtrl = new InventoryControl(userID, 1,Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true);
        int ID = inventoryControlRepository.add(invenCtrl);
        System.out.println(ID);
        
        invenCtrl.Id = ID; 
        InventoryControl result = (InventoryControl) inventoryControlRepository.getById(invenCtrl.Id);
        System.out.println(result);
        
        assertEquals(InventoryControl.class, result.getClass());
       
    }   
}
