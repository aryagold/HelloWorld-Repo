/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.ProductSale;

/**
 *
 * @author macpe
 */
public class ProductSaleRepositoryTest {
    
    private IRepository productSaleRepository;
    
    @BeforeEach
    void ProductSaleRepositoryTest() {
        this.productSaleRepository = new ProductRepository();
    }

    /**
     * Test of add method, of class ProductSaleRepository.
     */
    @Test
    public void testAdd() {
        
        assertEquals(true, productSaleRepository.add(new ProductSale("TestProd1","TestSale1")));
        
    }

    /**
     * Test of update method, of class ProductSaleRepository.
     */
    @Test
    public void testUpdate() {
        
        ProductSale prodSale = new ProductSale("TestProd1","TestSale1");
        productSaleRepository.add(prodSale);
        prodSale.setProductId("TestUpdateProd1");
        
        assertEquals(true, productSaleRepository.update(prodSale));
        
    }

    /**
     * Test of getById method, of class ProductSaleRepository.
     */
    @Test
    public void testGetById_int() {
        
        ProductSale productSale = new ProductSale("TestProd1","TestSale1");
        productSaleRepository.add(productSale);
        
        assertEquals(ProductSale.class, productSaleRepository.getById(productSale.Id) );
       
    }

    /**
     * Test of getById method, of class ProductSaleRepository.
     */
    @Test
    public void testGetById_String() {
       throw new UnsupportedOperationException("This mehtod cannot be tested yet");
    }
    
}
