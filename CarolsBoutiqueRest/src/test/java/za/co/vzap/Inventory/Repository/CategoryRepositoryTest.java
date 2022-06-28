/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Category;

/**
 *
 * @author macpe
 */
public class CategoryRepositoryTest {//complete
    
    private IRepository categoryRepository;
   
    @Test
    public void testAdd2() {// works
        
        categoryRepository = new CategoryRepository();
        
        Category category = new Category("Test Category");
        
        String result =  categoryRepository.add2(category) ;
       
        assertEquals( String.class ,result.getClass());
       
    }

    
    @Test
    public void testUpdate() {// works
        categoryRepository = new CategoryRepository();
       
        Category category = new Category("Testing123", "Test Category");
        String categoryID = categoryRepository.add2(category);
        
        category.setName("TestUpdateName");
        category.categoryId = categoryID;
        
        Boolean result =  categoryRepository.update(category);
        
        assertEquals(Boolean.class, result.getClass());
       
    }

    
    @Test
    public void testGetById_String() {// works
        categoryRepository = new CategoryRepository();
       
        Category category = new Category("Test1234","Test Category");
        String categoryID = categoryRepository.add2(category);
        
        category.categoryId = categoryID;
        
        Category result = (Category) categoryRepository.getById(category.categoryId);
        
        assertEquals(Category.class, result.getClass());
       
    }
}
