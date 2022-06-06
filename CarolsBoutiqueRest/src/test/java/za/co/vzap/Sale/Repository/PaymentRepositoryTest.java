/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.PaymentTypeEnum;

/**
 *
 * @author macpe
 */
public class PaymentRepositoryTest {
    
    private IRepository paymentRepository;
    
    @BeforeEach
    void PaymentRepositoryTest() {
        this.paymentRepository = new PaymentRepository();
    }

    @Test
    public void testAdd() {
        
     assertEquals(Integer.class, paymentRepository.add(new Payment(PaymentTypeEnum.CARD, "TestCard1", true)));  
       
    }

    
    @Test
    public void testUpdate() {
       
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard1", true);
        int ID = paymentRepository.add(payment);
        
        payment.Id = ID;
        payment.setCardNumber("TestCard2");
        
        assertEquals(Boolean.class, payment); 
       
    }

   
    @Test
    public void testGetById_int() {
        
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard1", true);
        int ID = paymentRepository.add(payment);
        
        payment.Id = ID;
        
        assertEquals(Payment.class, paymentRepository.getById(payment.Id));
       
    }
}
