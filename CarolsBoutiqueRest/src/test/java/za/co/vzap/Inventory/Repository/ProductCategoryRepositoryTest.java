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

    
    @Test
    public void testAdd() {
      
        assertEquals(Integer.class, productCategoryRepository.add(new ProductCategory("TestProd1","TestCat1")));
        
    }

   
    @Test
    public void testUpdate() {
        ProductCategory pc = new ProductCategory("TestProd1","TestCat1");
        int ID = productCategoryRepository.add(pc);
        
        pc.Id = ID;
        pc.setProductId("TestProd2");
        
        assertEquals(Boolean.class ,productCategoryRepository.update(pc));
        
    }

    
    @Test
    public void testGetById_int() {
        ProductCategory pc = new ProductCategory("TestProd1","TestCat1");
        int ID = productCategoryRepository.add(pc);
        
        pc.Id = ID;
        
        assertEquals(ProductCategory.class, productCategoryRepository.getById(pc.Id));
       
    }
}