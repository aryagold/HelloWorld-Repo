/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Customer.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

     
    @BeforeEach
    void CustomerRepositoryTest(){
        customerDB = new CustomerRepository();
    }
    
    
    @Test
    public void testAdd() {
        System.out.println("add");
        
        Customer customer = new Customer("Test@gmail.com","0000000000");
        
        Integer result = customerDB.add(customer);
        
        assertEquals(Integer.class, result.getClass());
    }

    
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Customer customer = new Customer("Test@gmail.com","0000000000");
        int customerID = customerDB.add(customer);
       
        customer.Id = customerID;
        customer.setPhoneNumber("NumberTest");
        
        boolean result = customerDB.update(customer);
        
        assertEquals( true , result);
    }

   
    @Test
    public void testGetById_int() {
        System.out.println("getById");
       
        Customer customer = new Customer("Test@gmail.com","0000000000");
        Integer result = customerDB.add(customer);
        
        assertEquals( Customer.class , result.getClass());
        
    }
}
