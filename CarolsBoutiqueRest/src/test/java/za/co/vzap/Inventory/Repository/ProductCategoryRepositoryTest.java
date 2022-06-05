/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.ProductCategory;

/**
 *
 * @author macpe
 */
public class ProductCategoryRepositoryTest {
    
    private IRepository productCategoryRepository;
    
    @BeforeEach
    void ProductCategoryRepositoryTest() {
        this.productCategoryRepository = new ProductCategoryRepository();
    }

    /**
     * Test of add method, of class ProductCategoryRepository.
     */
    @Test
    public void testAdd() {
      
        assertEquals(true, productCategoryRepository.add(new ProductCategory("TestProd1","TestCat1")));
        
    }

    /**
     * Test of update method, of class ProductCategoryRepository.
     */
    @Test
    public void testUpdate() {
        ProductCategory pc = new ProductCategory("TestProd1","TestCat1");
        productCategoryRepository.add(pc);
        
        pc.setProductId("TestProd2");
        
        assertEquals(true,productCategoryRepository.update(pc));
        
    }

    /**
     * Test of getById method, of class ProductCategoryRepository.
     */
    @Test
    public void testGetById_int() {
        ProductCategory pc = new ProductCategory("TestProd1","TestCat1");
        productCategoryRepository.add(pc);
        
        assertEquals(ProductCategory.class, productCategoryRepository.getById(pc.Id));
       
    }

    /**
     * Test of getById method, of class ProductCategoryRepository.
     */
    @Test
    public void testGetById_String() {
        throw new UnsupportedOperationException("This method cannot be tested at this time");
    }

}