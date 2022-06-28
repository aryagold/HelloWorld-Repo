/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.User.Service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Model.UserDto;
import za.co.vzap.User.Repository.UserRepository;

/**
 *
 * @author macpe
 */
public class UserServiceTest {//complete
    
    private IUserService userService;
    private IRepository userRepository;
    private IRepository branchRepository;

    
    
    @Test
    public void testUpdateToTeller() {//done
        
        userRepository = new UserRepository();
        branchRepository = new BranchRepository();
        
        userService = new UserService(userRepository, branchRepository);
        
        Branch branch = new Branch("TestBranch", 10000, 1000);
        String branchID = branchRepository.add2(branch);
        User user = new User("TestUser", "Test@email", branchID, "TestPassword", RoleEnum.GENERAL_EMPLOYEE);
        String userID = userRepository.add2(user);
        
        Boolean result = userService.updateToTeller(userID);
        
        assertEquals( Boolean.class , result.getClass());
        
    }

    @Test
    public void testAddBranch() {//done
        
        userRepository = new UserRepository();
        branchRepository = new BranchRepository();
        
        userService = new UserService(userRepository, branchRepository);
         
        Branch branch = new Branch("TestBranch", 10000, 1000);
        
        String result = userService.addBranch(branch);
        
        assertEquals(String.class, result.getClass());
        
    }

    @Test
    public void testLogin() {//done
        
        userRepository = new UserRepository();
        branchRepository = new BranchRepository();
        
        userService = new UserService(userRepository, branchRepository);
        
        Branch branch = new Branch("TestBranch", 10000, 1000);
        String branchID = branchRepository.add2(branch);
        User user = new User("TestUser", "Test@email", branchID, "TestPassword", RoleEnum.GENERAL_EMPLOYEE);
        String userID = userRepository.add2(user);
        
        user = null;
        user = (User) userRepository.getById(userID);
        
        UserDto result = userService.login(new User(user.getUserId(), user.getPassword()));
        
        assertEquals(UserDto.class, result.getClass());
        
    }
    
    @Test
    public void testGetAllBranches(){//done
        
        userRepository = new UserRepository();
        branchRepository = new BranchRepository();

        userService = new UserService(userRepository, branchRepository);
        
        List<Branch> branches = userService.getAllBranches();

        assertEquals(ArrayList.class,branches.getClass());
        
    }
    
}
 