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

     /*
     * Test of add method, of class ReviewRepository.
     */
    @BeforeEach
    void CustomerRepositoryTest(){
        reviewDB = new ReviewRepository();
    }
    
    @Test
    public void testAdd() {
        System.out.println("Testing add method");
        assertEquals(true,reviewDB.add(new Review("nice branch",10, "1234Test")));
    }

    /**
     * Test of update method, of class ReviewRepository.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing update method");
        
        Review review = new Review("nice branch",10, "1234Test");
        reviewDB.add(review);
        review.setComment("OK branch");
        
        assertEquals(true, reviewDB.update(review) );
        
    }

    /**
     * Test of getById method, of class ReviewRepository.
     */
@Test
    public void testGetById_int() {
        System.out.println("Testing GetById(int id) method");
        
        Review review = new Review("nice branch",10, "1234Test");
        reviewDB.add(review);
        
        assertEquals(Review.class, reviewDB.getById(review.Id));
        
    }

    /**
     * Test of getById method, of class ReviewRepository.
     */
    @Test
    public void testGetById_String() {
     throw new UnsupportedOperationException("Cannot test this method yet");
    }
    
}

