/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.InventoryControl;

/**
 *
 * @author macpe
 */
public class InventoryControlRepositoryTest {
    
    private IRepository inventoryControlRepository ;
    
    @BeforeEach
    void InventoryControlRepositoryTest() {
        inventoryControlRepository = new InventoryControlRepository();
    }

    /**
     * Test of add method, of class InventoryControlRepository.
     */
    @Test
    public void testAdd() {
        
        assertEquals(true, inventoryControlRepository.add(new InventoryControl("TestUser1", "TestProduct1",Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true)));
       
    }

    /**
     * Test of update method, of class InventoryControlRepository.
     */
    @Test
    public void testUpdate() {
        
        InventoryControl invenCtrl = new InventoryControl("TestUser1", "TestProduct1",Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true);
        inventoryControlRepository.add(invenCtrl);
        invenCtrl.setIncomingQuantity(20);
        
        assertEquals(true, inventoryControlRepository.update(invenCtrl));
       
    }

    /**
     * Test of getById method, of class InventoryControlRepository.
     */
    @Test
    public void testGetById_int() {
        
        InventoryControl invenCtrl = new InventoryControl("TestUser1", "TestProduct1",Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true);
        inventoryControlRepository.add(invenCtrl);
        
        assertEquals(InventoryControl.class, inventoryControlRepository.getById(invenCtrl.Id));
       
    }

    /**
     * Test of getById method, of class InventoryControlRepository.
     */
    @Test
    public void testGetById_String() {
       throw new UnsupportedOperationException("This method cannot be tested yet.");
    }
    
}
