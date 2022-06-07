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
    public void test2Add() {// works
        
        Sale sale = new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1, "TestBranchID", SaleStatusEnum.RESERVED);
        
        String result = saleRepository.add2(sale);
        
        assertEquals( String.class , result.getClass() );
        
    }

    @Test
    public void testUpdate() {// works
        
        Sale sale = new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1 , "TestBranchID", SaleStatusEnum.RESERVED);
        String ID = saleRepository.add2(sale);
        
        sale.saleId = ID ;
        sale.setEmail("Test2@gmail.com");
        
        Boolean result = saleRepository.update(sale);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

    @Test
    public void testGetById_String() {// null pointer as the method returns a null.
         
        Sale sale = new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1 , "TestBranchID", SaleStatusEnum.RESERVED);
        String ID = saleRepository.add2(sale);
        
        sale.saleId = ID;
        
        Sale result =(Sale) saleRepository.getById(sale.saleId);
        System.out.println(result);
        
        assertEquals( Sale.class, result.getClass());
    }
}
