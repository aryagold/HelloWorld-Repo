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

    /**
     * Test of add method, of class IBTRepository.
     */
    @Test
    public void testAdd() {
       
        assertEquals(true, ibtRepository.add(new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED)));
        
    }

    /**
     * Test of update method, of class IBTRepository.
     */
    @Test
    public void testUpdate() {
        IBT ibt = new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED);
        ibtRepository.add(ibt);
        ibt.setQuantity(20);
        
        assertEquals(true,ibtRepository.update(ibt));
       
    }

    /**
     * Test of getById method, of class IBTRepository.
     */
    @Test
    public void testGetById_int() {
        IBT ibt = new IBT("TestBranchFrom", "TestBranchTo", "TestProd1", 2, "072Testing", IBTStatusEnum.REQUESTED);
        ibtRepository.add(ibt);
       
        assertEquals(IBT.class, ibtRepository.getById(ibt.Id));
       
    }

    /**
     * Test of getById method, of class IBTRepository.
     */
    @Test
    public void testGetById_String() {
       throw new UnsupportedOperationException("this method cannot be tested yet");
    }

}
