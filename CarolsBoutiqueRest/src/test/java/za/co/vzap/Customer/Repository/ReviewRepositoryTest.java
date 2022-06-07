/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Customer.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Interface.Repository.IRepository;

/**
 *
 * @author macpe
 */
public class ReviewRepositoryTest {
    
 private IRepository reviewDB;

     
    @BeforeEach
    void ReviewRepositoryTest(){
        reviewDB = new ReviewRepository();
    }
    
    
    @Test
    public void testAdd() {
        System.out.println("add");
        
        Review review = new Review("nice branch",10, "1234Test");
        Integer result = reviewDB.add(review);
        
        assertEquals( Integer.class , result.getClass());
        
    }

   
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Review review = new Review("nice branch",10, "1234Test");
        int reviewID = reviewDB.add(review);
        
        review.Id = reviewID;
        review.setComment("OK branch");
        
        Boolean result = reviewDB.update(review);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

    
    @Test
    public void testGetById_int() {
        System.out.println("getById_int");
        
        Review review = new Review("nice branch",10, "1234Test");
        int reviewID = reviewDB.add(review);
        
        review.Id = reviewID;
        Review result =(Review) reviewDB.getById(review.Id);
        
        assertEquals(Review.class, result.getClass());
        
    }
}

