/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

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
 
   
    @Test
    public void testAdd() {
       
        assertEquals( Integer.class , saleLineItemRepository.add(new SaleLineItem("TestProdID", 10)));
        
    }

    
    @Test
    public void testUpdate() {
       
        SaleLineItem saleLineItem = new SaleLineItem("TestProdID", 10);
        int ID = saleLineItemRepository.add(saleLineItem);
        
        saleLineItem.Id = ID;
        saleLineItem.setInventoryId(1234);
        
        assertEquals( Boolean.class , saleLineItemRepository.update(saleLineItem));
        
    }

    
    @Test
    public void testGetById_int() {
        
        SaleLineItem saleLineItem = new SaleLineItem("TestProdID", 10);
        int ID = saleLineItemRepository.add(saleLineItem);
        
        saleLineItem.Id = ID;
      
        assertEquals(SaleLineItem.class, saleLineItemRepository.getById(saleLineItem.Id));
       
    }
}