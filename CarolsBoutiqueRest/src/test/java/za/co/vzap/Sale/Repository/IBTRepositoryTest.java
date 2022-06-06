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
       
        assertEquals(Integer.class, ibtRepository.add(new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED)));
        
    }

   
    @Test
    public void testUpdate() {
        IBT ibt = new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED);
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
        ibt.setQuantity(20);
        
        assertEquals( Boolean.class ,ibtRepository.update(ibt));
       
    }

    
    @Test
    public void testGetById_int() {
        IBT ibt = new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED);
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
       
        assertEquals(IBT.class, ibtRepository.getById(ibt.Id));
       
    }
}
