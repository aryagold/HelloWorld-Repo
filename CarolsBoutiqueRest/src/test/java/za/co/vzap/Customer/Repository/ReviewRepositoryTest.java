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
        
        assertEquals( Integer.class ,reviewDB.add(new Review("nice branch",10, "1234Test")));
        
    }

   
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Review review = new Review("nice branch",10, "1234Test");
        int reviewID = reviewDB.add(review);//adding
        
        review.Id = reviewID;
        review.setComment("OK branch");//setting
        
        assertEquals( Boolean.class , reviewDB.update(review) );
        
    }

    
    @Test
    public void testGetById_int() {
        System.out.println("getById_int");
        
        Review review = new Review("nice branch",10, "1234Test");
        int reviewID = reviewDB.add(review);//adding
        
        review.Id = reviewID;//setting
        
        assertEquals(Review.class, reviewDB.getById(review.Id));
        
    }
}

