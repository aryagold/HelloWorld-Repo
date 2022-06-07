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
        
        SaleLineItem saleLineItem = new SaleLineItem("TestProdID", 10);
        
        Integer result = saleLineItemRepository.add(saleLineItem);
       
        assertEquals( Integer.class , result.getClass());
        
    }

    
    @Test
    public void testUpdate() {
       
        SaleLineItem saleLineItem = new SaleLineItem("TestProdID", 10);
        int ID = saleLineItemRepository.add(saleLineItem);
        
        saleLineItem.Id = ID;
        saleLineItem.setInventoryId(1234);
        
        Boolean result = saleLineItemRepository.update(saleLineItem);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

    
    @Test
    public void testGetById_int() {
        
        SaleLineItem saleLineItem = new SaleLineItem("TestProdID", 10);
        int ID = saleLineItemRepository.add(saleLineItem);
        
        saleLineItem.Id = ID;
        
        SaleLineItem result  = (SaleLineItem) saleLineItemRepository.getById(saleLineItem.Id);
      
        assertEquals(SaleLineItem.class, result.getClass() );
       
    }
}