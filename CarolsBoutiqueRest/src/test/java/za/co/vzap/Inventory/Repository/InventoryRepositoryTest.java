/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;

/**
 *
 * @author macpe
 */
public class InventoryRepositoryTest {
    
    private IRepository inventoryRepository;
    
    @BeforeEach
    void InventoryRepositoryTest() {
        this.inventoryRepository = new InventoryRepository();
    }

   
    @Test
    public void testAdd() {
        
        Inventory inventory = new Inventory("TestBranch1234", 1, "PR001", "TestBarcode", 10);
        
        Integer result = inventoryRepository.add(inventory);
        
        assertEquals( Integer.class , result.getClass());
        
    }

    
    @Test
    public void testUpdate() {
       
        Inventory inven = new Inventory("TestBranch1234", 1, "PR001", "TestBarcode", 10);
        int ID = inventoryRepository.add(inven);
        
        inven.Id = ID;
        inven.setBarcode("TestBarcode2");
        
        Boolean result = inventoryRepository.update(inven);
        
        assertEquals( Boolean.class , result.getClass() );
       
    }

    
    @Test
    public void testGetById_int() {
        
        Inventory inven = new Inventory("TestBranch1234", 1, "PR001", "TestBarcode", 10);
        int ID = inventoryRepository.add(inven);
        
        inven.Id = ID;
        
        Inventory result = (Inventory) inventoryRepository.getById(inven.Id);
        
        assertEquals(Inventory.class, result.getClass() );
       
    }
}
