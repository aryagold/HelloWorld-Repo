/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.ProductCategory;

/**
 *
 * @author macpe
 */
public class ProductCategoryRepositoryTest {//complete
    
    private IRepository productCategoryRepository;
    private IRepository productDB;
    private IRepository categoryDB;
    
    @Test
    public void testAdd() {
        productCategoryRepository = new ProductCategoryRepository();
        productDB = new ProductRepository();
        categoryDB = new CategoryRepository();
        
        Product product = new Product("TestProduct1",100.00);
        String productID = productDB.add2(product);
        Category category = new Category("TestCategory");
        String categoryID = categoryDB.add2(category);
        
        ProductCategory pc = new ProductCategory(productID,categoryID);
        
        Integer result = productCategoryRepository.add(pc);
      
        assertEquals(Integer.class, result.getClass() );
        
    }

   
    @Test
    public void testUpdate() {
        productCategoryRepository = new ProductCategoryRepository();
        productDB = new ProductRepository();
        categoryDB = new CategoryRepository();
        
        Product product = new Product("TestProduct1",100.00);
        String productID = productDB.add2(product);
        Category category = new Category("TestCategory");
        String categoryID = categoryDB.add2(category);

        
        ProductCategory pc = new ProductCategory(productID,categoryID);
        int ID = productCategoryRepository.add(pc);
        
        pc.Id = ID;
        Product product2 = new Product("TestProduct2",100.00);
        String productID2 = productDB.add2(product2);
        pc.setProductId(productID2);
        
        Boolean result = productCategoryRepository.update(pc);
        
        assertEquals(Boolean.class , result.getClass() );
        
    }

    
    @Test
    public void testGetById_int() {
        productCategoryRepository = new ProductCategoryRepository();
        productDB = new ProductRepository();
        categoryDB = new CategoryRepository();
        
        Product product = new Product("TestProduct1",100.00);
        String productID = productDB.add2(product);
        Category category = new Category("TestCategory");
        String categoryID = categoryDB.add2(category);

        
        ProductCategory pc = new ProductCategory(productID,categoryID);
        int ID = productCategoryRepository.add(pc);
        
        pc.Id = ID;
        ProductCategory result = (ProductCategory)productCategoryRepository.getById(pc.Id);
        
        assertEquals(ProductCategory.class, result.getClass());
       
    }
}