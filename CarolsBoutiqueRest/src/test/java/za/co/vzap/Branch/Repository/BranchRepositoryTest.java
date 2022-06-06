/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Branch.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
  
    
    @Test
    public void testAdd2() {
        System.out.println("add");
        
        assertEquals(String.class, br.add2(new Branch("1234Test","TestBranch", 10000, 1000)));
        
    }

    
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Branch branch = new Branch("1234Test","TestBranch", 10000, 1000);
        String branchID = br.add2(branch);
        
        branch.branchId = branchID;
        branch.setDailyTarget(5000);
    
        assertEquals( Boolean.class , br.update(branch) ); 
       
    }
    
    @Test
    public void testGetById_String() {
        System.out.println("getById");
       
        Branch branch = new Branch("1234Test","TestBranch", 10000, 1000);
        br.add2(branch);
        
        assertEquals(Branch.class,br.getById(branch.branchId));
       
    }
    
}
