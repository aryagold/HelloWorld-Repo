/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Product;

/**
 *
 * @author macpe
 */
public class ProductRepositoryTest {//complete
    
    private IRepository productRepository;
   
    @Test
    public void testAdd2() {//done
        
        productRepository = new ProductRepository();
        
        Product product = new Product("TestProd1", 10.00);
        
        String result = productRepository.add2(product);
       
        assertEquals( String.class , result.getClass());
        
    }

   
    @Test
    public void testUpdate() {//done
        
        productRepository = new ProductRepository();
        
        Product product = new Product("TestProd1", "TestProd1", 10.00);
        String ID = productRepository.add2(product);
        
        product.productId = ID;
        product.setName("ChangedTestProd1");
        
        Boolean result = productRepository.update(product);
       
        assertEquals( Boolean.class , result.getClass() );
       
    }

    @Test
    public void testGetById_String() {//done
        
        productRepository = new ProductRepository();
       
        Product product = new Product("TestProd1", 10.00);
        String ID = productRepository.add2(product);
        
        product.productId = ID;
        
        Product result = (Product) productRepository.getById(ID);
        System.out.println(result);
        assertEquals(Product.class, result.getClass());
        
    }
}
