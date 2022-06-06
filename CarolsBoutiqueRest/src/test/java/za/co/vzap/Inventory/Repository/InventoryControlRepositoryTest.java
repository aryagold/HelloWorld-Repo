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

   
    @Test
    public void testAdd() {
        
        assertEquals( Integer.class , inventoryControlRepository.add(new InventoryControl("TestUser1", "TestProduct1",Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true)));
       
    }

    
    @Test
    public void testUpdate() {
        
        InventoryControl invenCtrl = new InventoryControl("TestUser1", "TestProduct1",Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true);
        int ID = inventoryControlRepository.add(invenCtrl);
        invenCtrl.setIncomingQuantity(20);
        invenCtrl.Id = ID;
        
        assertEquals( Boolean.class , inventoryControlRepository.update(invenCtrl));
       
    }

    
    @Test
    public void testGetById_int() {
        
        InventoryControl invenCtrl = new InventoryControl("TestUser1", "TestProduct1",Timestamp.valueOf(LocalDateTime.now()), 0, 10, 10, true);
        int ID = inventoryControlRepository.add(invenCtrl);
        
        invenCtrl.Id = ID; 
        
        assertEquals(InventoryControl.class, inventoryControlRepository.getById(invenCtrl.Id));
       
    }   
}
