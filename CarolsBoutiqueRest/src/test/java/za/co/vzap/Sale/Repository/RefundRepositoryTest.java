/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Sale.Model.Refund;

/**
 *
 * @author macpe
 */
public class RefundRepositoryTest {
    
    private IRepository refundRepository;
    
    public RefundRepositoryTest() {
        this.refundRepository = new RefundRepository();
    }

    /**
     * Test of add method, of class RefundRepository.
     */
    @Test
    public void testAdd() {
        
        assertEquals(true, refundRepository.add(new Refund("TestSaleId", Timestamp.valueOf(LocalDateTime.now()))));
        
    }

    /**
     * Test of update method, of class RefundRepository.
     */
    @Test
    public void testUpdate() {
        
        Refund refund = new Refund("TestSaleId", Timestamp.valueOf(LocalDateTime.now()));
        refundRepository.add(refund);
        refund.setSaleId("TestSaleId2");
        
        assertEquals(true, refundRepository.update(refund));
       
    }

    /**
     * Test of getById method, of class RefundRepository.
     */
    @Test
    public void testGetById_int() {
        
        Refund refund = new Refund("TestSaleId", Timestamp.valueOf(LocalDateTime.now()));
        refundRepository.add(refund);
        
        assertEquals(Refund.class, refundRepository.getById(refund.Id));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class RefundRepository.
     */
    @Test
    public void testGetById_String() {
       throw new UnsupportedOperationException("this method cannot be tested yet");
    }
    
}
