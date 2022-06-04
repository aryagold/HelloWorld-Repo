/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Category;

/**
 *
 * @author macpe
 */
public class CategoryRepositoryTest {
    
    private IRepository categoryRepository;
    
    @BeforeEach
    void CategoryRepositoryTest() {
        this.categoryRepository = new CategoryRepository();
    }
    
    /**
     * Test of add method, of class CategoryRepository.
     */
    @Test
    public void testAdd() {
       
        assertEquals(true, categoryRepository.add(new Category("Test Category")));
       
    }

    /**
     * Test of update method, of class CategoryRepository.
     */
    @Test
    public void testUpdate() {
       
        Category category = new Category("Testing123", "Test Category");
        categoryRepository.add(category);
        
        category.setName("TestUpdateName");
        
        assertEquals(true, categoryRepository.update(category));
       
    }

    /**
     * Test of getById method, of class CategoryRepository.
     */
    @Test
    public void testGetById_int() {
       throw new UnsupportedOperationException("Cannot run this test as yet");
    }

    /**
     * Test of getById method, of class CategoryRepository.
     */
    @Test
    public void testGetById_String() {
       
        Category category = new Category("Test1234","Test Category");
        categoryRepository.add(category);
        
        assertEquals(Category.class, categoryRepository.getById("Test1234"));
       
    }
    
}
