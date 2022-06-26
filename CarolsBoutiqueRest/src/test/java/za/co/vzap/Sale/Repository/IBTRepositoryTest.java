/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.POS.Model.IBT;
import za.co.vzap.POS.Model.IBTStatusEnum;

/**
 *
 * @author macpe
 */
public class IBTRepositoryTest {// complete
    
    private IRepository ibtRepository;
    
    @Test
    public void testAdd() {
        ibtRepository = new IBTRepository();
        
        IBT ibt = new IBT(61, "BR005" , 10 , "TestPhoneNumner" , "TestEmail" , IBTStatusEnum.REQUESTED);
        
        Integer result = ibtRepository.add(ibt);
       
        assertEquals(Integer.class, result.getClass() );
        
    }

   
    @Test
    public void testUpdate() {
        ibtRepository = new IBTRepository();
        
        IBT ibt = new IBT(61, "BR005", 10, "TestPhoneNumner", "TestEmail", IBTStatusEnum.REQUESTED);
             
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
        ibt.setQuantity(20);
        
        Boolean result = ibtRepository.update(ibt);
        
        assertEquals( Boolean.class , result.getClass() );
       
    }

    
    @Test
    public void testGetById_int() {
        ibtRepository = new IBTRepository();
        
        IBT ibt = new IBT(61, "BR005", 10, "TestPhoneNumner", "TestEmail", IBTStatusEnum.REQUESTED);
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
        
        IBT result = (IBT) ibtRepository.getById(ibt.Id);
        System.out.println(result);
       
        assertEquals(IBT.class, result.getClass());
       
    }
}
