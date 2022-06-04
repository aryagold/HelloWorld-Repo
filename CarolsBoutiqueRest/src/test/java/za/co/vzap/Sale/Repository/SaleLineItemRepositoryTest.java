/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Sale.Model.SaleLineItem;

/**
 *
 * @author macpe
 */
public class SaleLineItemRepositoryTest {
    
    private IRepository saleLineItemRepository;
    
    @BeforeEach
    void SaleLineItemRepositoryTest() {
        this.saleLineItemRepository = new SaleLineItemRepository();
    }
 
    /**
     * Test of add method, of class SaleLineItemRepository.
     */
    @Test
    public void testAdd() {
       
        assertEquals( true , saleLineItemRepository.add(new SaleLineItem("TestProdID", 10)));
        
    }

    /**
     * Test of update method, of class SaleLineItemRepository.
     */
    @Test
    public void testUpdate() {
       
        SaleLineItem saleLineItem = new SaleLineItem("TestProdID", 10);
        saleLineItemRepository.add(saleLineItem);
        saleLineItem.setQuantity(50);
        
        assertEquals( true , saleLineItemRepository.update(saleLineItem));
        
    }

    /**
     * Test of getById method, of class SaleLineItemRepository.
     */
    @Test
    public void testGetById_int() {
        
        SaleLineItem saleLineItem = new SaleLineItem("TestProdID", 10);
        saleLineItemRepository.add(saleLineItem);
      
        assertEquals(SaleLineItem.class, saleLineItemRepository.getById(saleLineItem.Id));
       
    }

    /**
     * Test of getById method, of class SaleLineItemRepository.
     */
    @Test
    public void testGetById_String() {
        throw new UnsupportedOperationException("This method cannot be tested yet.");
    }

}