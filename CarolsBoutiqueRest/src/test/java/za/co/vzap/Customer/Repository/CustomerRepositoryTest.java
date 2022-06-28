/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Customer.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Interface.Repository.IRepository;

/**
 *
 * @author macpe
 */
public class CustomerRepositoryTest {// complete
    private IRepository customerDB;

    
    @Test
    public void testAdd() {//works
        System.out.println("add");
        customerDB = new CustomerRepository();
        
        Customer customer = new Customer("Test@gmail.com","0000000000");
        
        Integer result = customerDB.add(customer);
        
        assertEquals(Integer.class, result.getClass());
    }

    
    @Test
    public void testUpdate() {// works
        System.out.println("update");
        customerDB = new CustomerRepository();
        
        Customer customer = new Customer("Test@gmail.com","0000000000");
        int customerID = customerDB.add(customer);
       
        customer.Id = customerID;
        customer.setPhoneNumber("NumberTest");
        
        boolean result = customerDB.update(customer);
        
        assertEquals( true , result);
    }

   
    @Test
    public void testGetById_int() {// works
        System.out.println("getById");
        customerDB = new CustomerRepository();
       
        Customer customer = new Customer("Test@gmail.com","0000000000");
        int ID  = customerDB.add(customer);
        
        Customer result = (Customer) customerDB.getById(ID);
        
        assertEquals( Customer.class , result.getClass());
        
    }
}
