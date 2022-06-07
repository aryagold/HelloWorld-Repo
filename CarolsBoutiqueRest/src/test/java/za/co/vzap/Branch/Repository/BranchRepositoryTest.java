/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Branch.Repository;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
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
        
        Branch branch = new Branch("1234Test","TestBranch", 10000, 1000);
        String result = br.add2(branch);
        
        assertEquals(String.class, result.getClass());
        
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Branch branch = new Branch("1234Test","TestBranch", 10000, 1000);
        String branchID = br.add2(branch);
        
        branch.branchId = branchID;
        branch.setDailyTarget(5000);
        
        boolean result =  br.update(branch);
    
        assertEquals( true , result ); 
       
    }
    
    @Test
    public void testGetById_String() {
        System.out.println("getById");
       
        Branch branch = new Branch("1234Test","TestBranch", 10000, 1000);
        br.add2(branch);
         
        Branch result = (Branch) br.getById(branch.branchId);
        
        assertEquals(Branch.class, result.getClass());
       
    }
    
}
