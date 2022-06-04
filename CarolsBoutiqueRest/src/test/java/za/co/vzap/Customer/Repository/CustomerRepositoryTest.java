/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Customer.Repository;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Interface.Repository.IRepository;

/**
 *
 * @author macpe
 */
public class CustomerRepositoryTest {
    private IRepository customerDB;

     /*
     * Test of add method, of class CustomerRepository.
     */
    @BeforeEach
    void CustomerRepositoryTest(){
        customerDB = new CustomerRepository();
    }
    
    @Test
    public void testAdd() {
        Customer customer = new Customer("Test@gmail.com","0000000000");
        
        assertEquals(true,customerDB.add(customer));
    }

    /**
     * Test of update method, of class CustomerRepository.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Customer customer = new Customer("Test@gmail.com","0000000000");
        customerDB.add(customer);
       
        customer.setPhoneNumber("0724567289");
        
        assertEquals(true,customerDB.update(customer));
    }

    /**
     * Test of getById method, of class CustomerRepository.
     */
    @Test
    public void testGetById_int() {
        System.out.println("getById");
       
        Customer customer = new Customer("Test@gmail.com","0000000000");
        customerDB.add(customer);
        
        
        
        assertEquals(Customer.class, customerDB.getById(customer.Id));
        
    }

    /**
     * Test of getById method, of class CustomerRepository.
     */
    @Test
    public void testGetById_String() {
      throw new UnsupportedOperationException("Cannot test this method yet");
    }
    
}
