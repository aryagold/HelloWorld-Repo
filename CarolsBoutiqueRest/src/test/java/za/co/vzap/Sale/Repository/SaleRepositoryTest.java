/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleStatusEnum;

/**
 *
 * @author macpe
 */
public class SaleRepositoryTest {
    
    private IRepository saleRepository;
    
    public SaleRepositoryTest() {
        this.saleRepository = new SaleRepository();
    }

   
    @Test
    public void test2Add() {
        
        assertEquals( String.class , saleRepository.add2(new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1, "TestBranchID", SaleStatusEnum.RESERVED)));
        
    }

    @Test
    public void testUpdate() {
        
        Sale sale = new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1 , "TestBranchID", SaleStatusEnum.RESERVED);
        String ID = saleRepository.add2(sale);
        
        sale.saleId = ID ;
        sale.setEmail("Test2@gmail.com");
        
        assertEquals( Boolean.class , saleRepository.update(sale));
        
    }

    @Test
    public void testGetById_String() {
         
        Sale sale = new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1 , "TestBranchID", SaleStatusEnum.RESERVED);
        String ID = saleRepository.add2(sale);
        
        sale.saleId = ID;
        
        assertEquals( Sale.class, sale.saleId);
    }
}
