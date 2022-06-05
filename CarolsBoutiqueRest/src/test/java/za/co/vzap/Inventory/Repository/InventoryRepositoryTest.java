/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import java.util.List;
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

    /**
     * Test of add method, of class InventoryRepository.
     */
    @Test
    public void testAdd() {
        
        assertEquals(true, inventoryRepository.add(new Inventory("TestBranch1234", 1, 1234, "TestBarcode", 10)));
        
    }

    /**
     * Test of update method, of class InventoryRepository.
     */
    @Test
    public void testUpdate() {
       
        Inventory inven = new Inventory("TestBranch1234", 1, 1234, "TestBarcode", 10);
        inventoryRepository.add(inven);
        inven.setBarcode("TestBarcode2");
        
        assertEquals(true, inventoryRepository.update(inven));
       
    }

    /**
     * Test of getById method, of class InventoryRepository.
     */
    @Test
    public void testGetById_int() {
        
        Inventory inven = new Inventory("TestBranch1234", 1, 1234, "TestBarcode", 10);
        
        assertEquals(Inventory.class, inventoryRepository.getById(inven.Id));
       
    }

    /**
     * Test of getById method, of class InventoryRepository.
     */
    @Test
    public void testGetById_String() {
       throw new UnsupportedOperationException("This class cannot be tested yet");
    }
    
}
