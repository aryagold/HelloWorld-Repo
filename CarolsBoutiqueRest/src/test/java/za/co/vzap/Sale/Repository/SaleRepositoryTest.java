/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    /**
     * Test of add method, of class SaleRepository.
     */
    @Test
    public void testAdd() {
        
        assertEquals( true , saleRepository.add(new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1, "TestBranchID", SaleStatusEnum.RESERVED)));
        
    }

    /**
     * Test of update method, of class SaleRepository.
     */
    @Test
    public void testUpdate() {
        
        Sale sale = new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1 , "TestBranchID", SaleStatusEnum.RESERVED);
        saleRepository.add(sale);
        sale.setEmail("Test2@gmail.com");
        
        assertEquals( true , saleRepository.update(sale));
        
    }

    /**
     * Test of getById method, of class SaleRepository.
     */
    @Test
    public void testGetById_int() {
        throw new UnsupportedOperationException("This method cannot be tested yet.");
    }

    /**
     * Test of getById method, of class SaleRepository.
     */
    @Test
    public void testGetById_String() {
         
        Sale sale = new Sale("TestSaleID", "TestUserID", "Test@gmail.com", Timestamp.valueOf(LocalDateTime.now()), 1 , "TestBranchID", SaleStatusEnum.RESERVED);
        saleRepository.add(sale);
        
        assertEquals( Sale.class, sale.saleId);
    }
}
