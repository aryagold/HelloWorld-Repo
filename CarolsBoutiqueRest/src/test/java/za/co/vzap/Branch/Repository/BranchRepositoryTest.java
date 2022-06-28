/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Branch.Repository;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Interface.Repository.IRepository;

/**
 *
 * @author macpe
 */
public class BranchRepositoryTest {// branch complete
    private IRepository br;
  
    @Test
    public void testAdd2() {// works
        System.out.println("add");
        br = new BranchRepository();
        
        Branch branch = new Branch("TestBranch", 10000, 1000);
       
        String result = br.add2(branch) ;
        
        assertEquals(String.class, result.getClass());
        
    }

    @Test
    public void testUpdate() {//works
        System.out.println("update");
        br = new BranchRepository();
        
        Branch branch = new Branch("TestBranch", 10000, 1000);
        String branchID = br.add2(branch);
        
        branch.branchId = branchID;
        branch.setDailyTarget(5000);
        
        boolean result =  br.update(branch);
    
        assertEquals( true , result ); 
       
    }
    
    @Test
    public void testGetById_String() {// works
        System.out.println("getById");
        br = new BranchRepository();
       
        Branch branch = new Branch("TestBranch", 10000.00, 1000.00);
        String branchID = br.add2(branch);
         
        
        Branch result = (Branch) br.getById(branchID);
        
        assertEquals(Branch.class, result.getClass());
       
    }
    
}
