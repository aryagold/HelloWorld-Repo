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

    /**
     * Test of add method, of class ProductCodeRepository.
     */
    @Test
    public void testAdd() {
        
        assertEquals(true, productCodeRepository.add(new ProductCode("TestProd")));
       
    }

    /**
     * Test of update method, of class ProductCodeRepository.
     */
    @Test
    public void testUpdate() {
       
        ProductCode prodCode = new ProductCode("TestProd");
        productCodeRepository.add(prodCode);
        prodCode.setProductCode("Testprod2");
        
        assertEquals(true, productCodeRepository.update(prodCode));
        
    }

    /**
     * Test of getById method, of class ProductCodeRepository.
     */
    @Test
    public void testGetById_int() {
       
        ProductCode prodCode = new ProductCode("TestProd1");
        productCodeRepository.add(prodCode);
        
        assertEquals(ProductCode.class, productCodeRepository.getById(prodCode.Id));
    }

    /**
     * Test of getById method, of class ProductCodeRepository.
     */
    @Test
    public void testGetById_String() {
       throw new UnsupportedOperationException("This method cannot be tested yet");
    }
    
}
