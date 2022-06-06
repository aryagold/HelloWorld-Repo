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
        
        assertEquals(Integer.class,customerDB.add(customer));
    }

    
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Customer customer = new Customer("Test@gmail.com","0000000000");
        int customerID = customerDB.add(customer);
       
        customer.Id = customerID;//setting customer attributes to new values
        customer.setPhoneNumber("NumberTest");
        
        assertEquals( Boolean.class ,customerDB.update(customer));
    }

   
    @Test
    public void testGetById_int() {
        System.out.println("getById");
       
        Customer customer = new Customer("Test@gmail.com","0000000000");
        int customerID = customerDB.add(customer);
        
        customer.Id = customerID;
        
        assertEquals( Customer.class , customerDB.getById(customer.Id));
        
    }
}
