/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.User.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;

/**
 *
 * @author macpe
 */
public class UserRepositoryTest {
    
    private IRepository userDB;
    private IRepository branchDB;
    
    @Test
    public void testAdd2() {
        
        userDB = new UserRepository();
        branchDB = new BranchRepository();
       
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        
        User user = new User("TestUser","TestUserEmail",branchID,"TestPassword",RoleEnum.TELLER);
        String result = userDB.add2(user);
        
        assertEquals(String.class, result.getClass());
        
    }

    @Test
    public void testUpdate() {
        
        userDB = new UserRepository();
        branchDB = new BranchRepository();
       
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        
        User user = new User("TestUser","TestUserEmail",branchID,"TestPassword",RoleEnum.TELLER);
        String userID = userDB.add2(user);
        
        user.setEmail("TestEmail2");
        
        Boolean result = userDB.update(user);
        
        assertEquals(Boolean.class, result.getClass());
       
    }

    @Test
    public void testGetById_String() {
       
        userDB = new UserRepository();
        branchDB = new BranchRepository();
       
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        
        User user = new User("TestUser","TestUserEmail",branchID,"TestPassword",RoleEnum.TELLER);
        String userID = userDB.add2(user);
        
        User result = (User) userDB.getById(userID);
   
        assertEquals( User.class , result.getClass() );
        
    }
    
}
