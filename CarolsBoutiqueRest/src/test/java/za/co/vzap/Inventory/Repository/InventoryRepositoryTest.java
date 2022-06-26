/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;

/**
 *
 * @author macpe
 */
public class InventoryRepositoryTest {//complete
    
    private IRepository inventoryRepository;
    private IRepository branchDB;
    private IRepository sizeDB;
    private IRepository productDB;
   
    @Test
    public void testAdd() {//works
        inventoryRepository = new InventoryRepository();
        branchDB = new BranchRepository();
        sizeDB = new SizeRepository();
        productDB = new ProductRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Size size = new Size("XL");
        int sizeID = sizeDB.add(size);
        Product product = new Product("TestProduct1", 10.00);
        String productID = productDB.add2(product);
        
        Inventory inventory = new Inventory(branchID, sizeID, productID, "TestBarcode", 10);
        
        Integer result = inventoryRepository.add(inventory);
        
        assertEquals( Integer.class , result.getClass());
        
    }

    
    @Test
    public void testUpdate() {//works
        inventoryRepository = new InventoryRepository();
        branchDB = new BranchRepository();
        sizeDB = new SizeRepository();
        productDB = new ProductRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Size size = new Size("XL");
        int sizeID = sizeDB.add(size);
        Product product = new Product("TestProduct1", 10.00);
        String productID = productDB.add2(product);
       
        Inventory inven = new Inventory(branchID, sizeID, productID, "TestBarcode", 10);
        int ID = inventoryRepository.add(inven);
        
        inven.Id = ID;
        inven.setBarcode("TestBarcode2");
        
        Boolean result = inventoryRepository.update(inven);
        
        assertEquals( Boolean.class , result.getClass() );
       
    }

    
    @Test
    public void testGetById_int() {//works
        inventoryRepository = new InventoryRepository();
        branchDB = new BranchRepository();
        sizeDB = new SizeRepository();
        productDB = new ProductRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Size size = new Size("XL");
        int sizeID = sizeDB.add(size);
        Product product = new Product("TestProduct1", 10.00);
        String productID = productDB.add2(product);
        
        Inventory inven = new Inventory(branchID,sizeID, productID, "TestBarcode", 10);
        int ID = inventoryRepository.add(inven);
        
        inven.Id = ID;
        
        Inventory result = (Inventory) inventoryRepository.getById(inven.Id);
        
        assertEquals(Inventory.class, result.getClass() );
       
    }
}
