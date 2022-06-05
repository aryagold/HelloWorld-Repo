/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Sale.Model.RefundItem;

/**
 *
 * @author macpe
 */
public class RefundItemRepositoryTest {
    
    private IRepository refundItemRepository;
    
    @BeforeEach
    void RefundItemRepositoryTest() {
        this.refundItemRepository = new RefundItemRepository();
    }

    /**
     * Test of add method, of class RefundItemRepository.
     */
    @Test
    public void testAdd() {
        
        assertEquals(true, refundItemRepository.add(new RefundItem("TestProdID", 20)));
  
    }

    /**
     * Test of update method, of class RefundItemRepository.
     */
    @Test
    public void testUpdate() {
        
        RefundItem refundItem = new RefundItem("TestProdID", 20);
        refundItemRepository.add(refundItem);
        refundItem.setQuantity(50);
        
        assertEquals(true, refundItemRepository.update(refundItem));
    }

    /**
     * Test of getById method, of class RefundItemRepository.
     */
    @Test
    public void testGetById_int() {
        
        RefundItem refundItem = new RefundItem("TestProdID", 20);
        refundItemRepository.add(refundItem);
        
        assertEquals(RefundItem.class, refundItemRepository.getById(refundItem.Id));
        
    }

    /**
     * Test of getById method, of class RefundItemRepository.
     */
    @Test
    public void testGetById_String() {
        throw new UnsupportedOperationException("This method cannot be tested yet.");
    }
    
}
