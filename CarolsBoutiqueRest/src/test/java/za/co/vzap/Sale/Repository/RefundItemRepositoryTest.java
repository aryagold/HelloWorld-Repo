/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

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

    @Test
    public void testAdd() {
        
        RefundItem refundItem = new RefundItem("TestProdID", 20);
        
        Integer result = refundItemRepository.add(refundItem);
       
        assertEquals(Integer.class, refundItemRepository.add(new RefundItem("TestProdID", 20)));
  
    }

    
    @Test
    public void testUpdate() {
        
        RefundItem refundItem = new RefundItem("TestProdID", 20);
        int ID = refundItemRepository.add(refundItem);
        
        refundItem.Id = ID;
        refundItem.setProductId("TestProdID");
        
        Boolean result = refundItemRepository.update(refundItem);
        
        assertEquals( Boolean.class , result.getClass() );
    }

   
    @Test
    public void testGetById_int() {
        
        RefundItem refundItem = new RefundItem("TestProdID", 20);
        int ID = refundItemRepository.add(refundItem);
        
        refundItem.Id = ID;
        RefundItem result =  (RefundItem) refundItemRepository.getById(refundItem.Id);
        
        assertEquals(RefundItem.class, result.getClass() );
        
    }
}
