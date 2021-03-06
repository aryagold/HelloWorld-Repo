/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.POS.Model.Payment;
import za.co.vzap.POS.Model.PaymentTypeEnum;

/**
 *
 * @author macpe
 */
public class PaymentRepositoryTest {// complete
    
    private IRepository paymentRepository;
    
    @Test
    public void testAdd() {//done
        paymentRepository = new PaymentRepository();
        
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard1", true);
        
        Integer result = paymentRepository.add(payment);
        
        assertEquals(Integer.class, result.getClass());  
       
    }

    
    @Test
    public void testUpdate() {//done
        paymentRepository = new PaymentRepository();
       
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard1", true);
        int ID = paymentRepository.add(payment);
        
        payment.Id = ID;
        payment.setCardNumber("TestCard2");
        
        Boolean result = paymentRepository.update(payment);
        
        assertEquals(Boolean.class, result.getClass()); 
       
    }

   
    @Test
    public void testGetById_int() {//done
        paymentRepository = new PaymentRepository();
        
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard1", true);
        int ID = paymentRepository.add(payment);
        
        payment.Id = ID;
        
        Payment result = (Payment) paymentRepository.getById(payment.Id);
        
        assertEquals(Payment.class, result.getClass() );
       
    }
}
