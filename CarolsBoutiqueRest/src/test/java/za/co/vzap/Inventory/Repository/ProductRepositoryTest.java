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

   
    @Test
    public void testAdd2() {
       
        assertEquals( Integer.class , productRepository.add(new Product("TestProd1", "TestProd1", 10.00)));
        
    }

   
    @Test
    public void testUpdate() {
        
        Product product = new Product("TestProd1", "TestProd1", 10.00);
        String ID = productRepository.add2(product);
        
        product.productId = ID;
        product.setName("ChangedTestProd1");
       
        assertEquals( Boolean.class , productRepository.update(product));
       
    }

    @Test
    public void testGetById_String() {
       
        Product product = new Product("TestProd1", "TestProd1", 10.00);
        String ID = productRepository.add2(product);
        
        product.productId = ID;
        
        assertEquals(Product.class, productRepository.getById("TestProd1"));
        
    }
}
