/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Product;

/**
 *
 * @author macpe
 */
public class ProductRepositoryTest {
    
    private IRepository productRepository;
    
    @BeforeEach
    void ProductRepositoryTest() {
        this.productRepository = new ProductRepository();
    }

    /**
     * Test of add method, of class ProductRepository.
     */
    @Test
    public void testAdd() {
       
        assertEquals(true, productRepository.add(new Product("TestProd1", "TestProd1", 10.00)));
        
    }

    /**
     * Test of update method, of class ProductRepository.
     */
    @Test
    public void testUpdate() {
        
        Product product = new Product("TestProd1", "TestProd1", 10.00);
        productRepository.add(product);
        product.setName("ChangedTestProd1");
       
        assertEquals(true, productRepository.update(product));
       
    }

    /**
     * Test of getById method, of class ProductRepository.
     */
    @Test
    public void testGetById_int() {
       throw new UnsupportedOperationException("This method cannot be tested yet");
    }

    /**
     * Test of getById method, of class ProductRepository.
     */
    @Test
    public void testGetById_String() {
       
        Product product = new Product("TestProd1", "TestProd1", 10.00);
        productRepository.add(product);
        
        assertEquals(Product.class, productRepository.getById("TestProd1"));
        
    }
    
}
