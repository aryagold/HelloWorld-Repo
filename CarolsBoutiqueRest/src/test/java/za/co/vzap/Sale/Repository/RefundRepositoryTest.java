/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import org.junit.Test;
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

   
    @Test
    public void testAdd() {
        
        assertEquals(Integer.class, refundRepository.add(new Refund("TestSaleId", Timestamp.valueOf(LocalDateTime.now()))));
        
    }

    
    @Test
    public void testUpdate() {
        
        Refund refund = new Refund("TestSaleId", Timestamp.valueOf(LocalDateTime.now()));
        int ID = refundRepository.add(refund);
        
        refund.Id = ID;
        refund.setSaleId("TestSaleId2");
        
        assertEquals( Boolean.class , refundRepository.update(refund));
       
    }

    
    @Test
    public void testGetById_int() {
        
        Refund refund = new Refund("TestSaleId", Timestamp.valueOf(LocalDateTime.now()));
        int ID = refundRepository.add(refund);
        
        refund.Id = ID;
        
        assertEquals(Refund.class, refundRepository.getById(refund.Id));
        
    }
}
