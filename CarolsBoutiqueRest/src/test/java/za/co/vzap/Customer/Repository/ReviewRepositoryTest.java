/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Customer.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Interface.Repository.IRepository;

/**
 *
 * @author macpe
 */
public class ReviewRepositoryTest {
    
 private IRepository reviewDB;
 private IRepository branchDB;
    
    @Test
    public void testAdd() {// works
        System.out.println("add");
        reviewDB = new ReviewRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch",10000,1000);
        String branchID = branchDB.add2(branch);
        
        Review review = new Review("nice branch",10, branchID);
        Integer result = reviewDB.add(review);
        
        assertEquals( Integer.class , result.getClass());
        
    }

   
    @Test
    public void testUpdate() {// works
        System.out.println("update");
        reviewDB = new ReviewRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch",10000,1000);
        String branchID = branchDB.add2(branch);
        
        Review review = new Review("nice branch",10, branchID);
        int reviewID = reviewDB.add(review);
        
        review.Id = reviewID;
        review.setComment("OK branch");
        
        Boolean result = reviewDB.update(review);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

    
    @Test
    public void testGetById_int() {//works
        System.out.println("getById_int");
        reviewDB = new ReviewRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch",10000,1000);
        String branchID = branchDB.add2(branch);
        
        Review review = new Review("nice branch",10, branchID);
        int reviewID = reviewDB.add(review);
        
        review.Id = reviewID;
        Review result =(Review) reviewDB.getById(review.Id);
        
        assertEquals(Review.class, result.getClass());
        
    }
}

