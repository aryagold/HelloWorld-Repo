/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.ProductCode;

/**
 *
 * @author macpe
 */
public class ProductCodeRepositoryTest {
    
    private IRepository productCodeRepository;
    
    @BeforeEach
    void ProductCodeRepositoryTest() {
        this.productCodeRepository = new ProductCodeRepository();
    }

    
    @Test
    public void testAdd() {
        
        ProductCode pc = new ProductCode("TestProd");
        
        Integer result = productCodeRepository.add(pc);
        
        assertEquals(Integer.class , result.getClass() );
       
    }

   
    @Test
    public void testUpdate() {
       
        ProductCode prodCode = new ProductCode("TestProd");
        int ID = productCodeRepository.add(prodCode);
        
        prodCode.Id = ID;
        prodCode.setProductCode("Testprod2");
        
        Boolean result =  productCodeRepository.update(prodCode);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

   
    @Test
    public void testGetById_int() {
       
        ProductCode prodCode = new ProductCode("TestProd1");
        int ID = productCodeRepository.add(prodCode);
        
        prodCode.Id = ID;
        
        ProductCode result = (ProductCode) productCodeRepository.getById(prodCode.Id);
        
        assertEquals(ProductCode.class, result.getClass() );
    }
}
