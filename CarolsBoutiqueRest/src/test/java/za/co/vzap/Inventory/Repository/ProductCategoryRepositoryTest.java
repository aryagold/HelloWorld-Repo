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
        
        ProductCategory pc = new ProductCategory("TestProd1","TestCat1");
        
        Integer result = productCategoryRepository.add(pc);
      
        assertEquals(Integer.class, result.getClass() );
        
    }

   
    @Test
    public void testUpdate() {
        ProductCategory pc = new ProductCategory("TestProd1","TestCat1");
        int ID = productCategoryRepository.add(pc);
        
        pc.Id = ID;
        pc.setProductId("TestProd2");
        
        Boolean result = productCategoryRepository.update(pc);
        
        assertEquals(Boolean.class , result.getClass() );
        
    }

    
    @Test
    public void testGetById_int() {
        ProductCategory pc = new ProductCategory("TestProd1","TestCat1");
        int ID = productCategoryRepository.add(pc);
        
        pc.Id = ID;
        ProductCategory result = (ProductCategory)productCategoryRepository.getById(pc.Id);
        
        assertEquals(ProductCategory.class, result.getClass());
       
    }
}