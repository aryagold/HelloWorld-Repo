/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Branch.Repository;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Interface.Repository.IRepository;

/**
 *
 * @author macpe
 */
public class BranchRepositoryTest {
    private IRepository br;
    
    @BeforeEach
    void BranchRepositoryTest() {
        br = new BranchRepository();
    }
    /**
     * Test of add method, of class BranchRepository.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        assertEquals(true, br.add(new Branch("test", 0, 0)));
    }

    /**
     * Test of update method, of class BranchRepository.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Branch branch = new Branch("1234","test", 0, 0);
        br.add(branch);
        branch.setDailyTarget(5000);
    
        assertEquals(true,br.update(branch)); 
       
    }

    /**
     * Test of getById method, of class BranchRepository.
     */
    @Test
    public void testGetById_int() {
       throw new UnsupportedOperationException("You cannot test this method right now");
    }

    /**
     * Test of getById method, of class BranchRepository.
     */
    @Test
    public void testGetById_String() {
        System.out.println("getById");
       
        br.add(new Branch("1234Test","test", 0, 0));
        
        assertEquals(Branch.class,br.getById("1234Test"));
       
    }

    
}
