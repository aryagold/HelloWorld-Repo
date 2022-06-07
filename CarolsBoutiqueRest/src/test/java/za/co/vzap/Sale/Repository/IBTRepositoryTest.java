/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IBTStatusEnum;

/**
 *
 * @author macpe
 */
public class IBTRepositoryTest {
    
    private IRepository ibtRepository;
    
    @BeforeEach
    void IBTRepositoryTest() {
        this.ibtRepository = new IBTRepository();
    }

   
    @Test
    public void testAdd() {
        
        IBT ibt = new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED);
        
        Integer result = ibtRepository.add(ibt);
       
        assertEquals(Integer.class, result.getClass() );
        
    }

   
    @Test
    public void testUpdate() {
        IBT ibt = new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED);
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
        ibt.setQuantity(20);
        
        Boolean result = ibtRepository.update(ibt);
        
        assertEquals( Boolean.class , result.getClass() );
       
    }

    
    @Test
    public void testGetById_int() {
        IBT ibt = new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED);
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
        
        IBT result = (IBT) ibtRepository.getById(ibt.Id);
       
        assertEquals(IBT.class, result.getClass());
       
    }
}
