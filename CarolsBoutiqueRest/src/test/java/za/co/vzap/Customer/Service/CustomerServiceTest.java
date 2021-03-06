/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Customer.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Customer.Repository.CustomerRepository;
import za.co.vzap.Customer.Repository.ReviewRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.ICustomerService;

/**
 *
 * @author macpe
 */
public class CustomerServiceTest {//complete
    
    private ICustomerService customerService;
    private IRepository reviewRepository;
    private IRepository customerRepository;
    private IRepository branchDB;
 
    @Test
    public void testAddCustomer() {//done
        
        reviewRepository = new ReviewRepository();
        customerRepository = new CustomerRepository();
        
        customerService = new CustomerService(reviewRepository, customerRepository);
      
        Customer customer = new Customer("TestEmail", "072Testing");
        Integer result = customerService.addCustomer(customer);
        
        assertEquals(Integer.class, result.getClass());
        
    }
   
    @Test
    public void testAddReview() {//done
      
       reviewRepository = new ReviewRepository();
       customerRepository = new CustomerRepository(); 
       branchDB = new BranchRepository();
       
       customerService = new CustomerService(reviewRepository, customerRepository);
        
       Branch branch = new Branch("TestBranch", 10000, 1000);
       String branchID = branchDB.add2(branch);
       
       Review review = new Review("good service", 5, branchID, Timestamp.valueOf(LocalDateTime.now()));
       
       Integer result = customerService.addReview(review);
       
        assertEquals(Integer.class, result.getClass());
       
    }
}
