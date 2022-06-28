/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Report.Service;

import static org.junit.Assert.*;
import org.junit.Test;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Report.Model.CustomerReportsDto;
import za.co.vzap.Report.Model.LeastPerformingStoresDto;
import za.co.vzap.Report.Model.StatementDto;
import za.co.vzap.Report.Model.StoreSalesDto;
import za.co.vzap.Report.Model.StoresAtTargetDto;
import za.co.vzap.Report.Model.TopAchievingStoresDto;
import za.co.vzap.Report.Model.TopEmployeesDto;


/**
 *
 * @author macpe
 */
public class ReportServiceTest {// 2 tests incomplete as service is incomplete.
    
    private IReportService reportService;
    
    @Test
    public void testTopAchievingStores() {
        
        reportService = new ReportService();
        
        TopAchievingStoresDto result = reportService.topAchievingStores();
        
        assertEquals( TopAchievingStoresDto.class , result.getClass() );
        
    }

    @Test
    public void testGetCustomerReport() {
        
        reportService = new ReportService();
        
        CustomerReportsDto result = reportService.getCustomerReport("January", 100);

        assertEquals( CustomerReportsDto.class , result.getClass() );
        
    }
    
    @Test
    public void testStoreSalesByMonth(){
        
        reportService = new ReportService();
        
        StoreSalesDto result = reportService.storeSalesByMonth("BR001", "January");

        assertEquals(StoreSalesDto.class , result.getClass());
        
    }

    @Test
    public void testTopSellingEmployees(){
        
        reportService = new ReportService();
        
        TopEmployeesDto result = reportService.topSellingEmployees("BR001");

        assertEquals(TopEmployeesDto.class, result.getClass());
        
    }
    
    @Test
    public void testStoresAtTarget(){
        
        reportService = new ReportService();
        
//        StoresAtTargetDto result = reportService.storesAtTarget();
//        
//        assertEquals(StoresAtTargetDto.class, result.getClass());
        
    }
    
    @Test
    public void testTopFourtyProductsDto(){
        
    }
    
    @Test
    public void testLeastPerformingStoresDto(){
        
        reportService = new ReportService();
        
        LeastPerformingStoresDto result = reportService.getLeastPerforming(3);
     
        assertEquals(LeastPerformingStoresDto.class, result.getClass());
        
    }
    
    @Test
    public void testGetProductSales(){
        
        
        
    }
    
    @Test
    public void testStoreDailySales(){
        
        reportService = new ReportService();
        
        StoreSalesDto result = reportService.storeDailySales("BR001");
        
        assertEquals(StoreSalesDto.class, result.getClass());
        
    }
    
}