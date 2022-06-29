/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.Report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Model.Inventory.InventoryDto;
import za.co.vzap.Model.Report.CustomerReportsDto;
import za.co.vzap.Model.Report.LeastPerformingStoresDto;
import za.co.vzap.Model.Report.ProductSales;
import za.co.vzap.Model.Report.StoreSalesDto;
import za.co.vzap.Model.Report.StoresAtTargetDto;
import za.co.vzap.Model.Report.TopAchievingStoresDto;
import za.co.vzap.Model.Report.TopEmployeesDto;
import za.co.vzap.Model.Report.TopFourtyProducts;

/**
 *
 * @author macpe
 */
public class ReportService implements IReportService{
    
    private ObjectMapper om;
    private String url;
    private Client client;
    private WebTarget target;
    private Response response;

    public ReportService() {

        this.om = new ObjectMapper();

    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Override
    public TopAchievingStoresDto topAchievingStores() {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/topachievingstores";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get() ;
       
        return response.readEntity(TopAchievingStoresDto.class) ;
    
    }

    @Override
    public CustomerReportsDto getCustomerReport(String month, int resultAmount) {
        CustomerReportsDto dto = new CustomerReportsDto();
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/customerreport?month=" + month + "&resultAmount=" + resultAmount;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        try {
            dto = om.readValue(target.request().accept(MediaType.APPLICATION_JSON).get(String.class), CustomerReportsDto.class);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return dto;
    
    }

    @Override
    public StoreSalesDto storeSalesByMonth(String branchId , String month) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/salesformonth?branchId=" + branchId + "&month=" + month;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
    
        return response.readEntity(StoreSalesDto.class);
    
    }

    @Override
    public TopEmployeesDto topSellingEmployees(String branchId) {
    
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/topsellingemployees?branchId=" + branchId;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(TopEmployeesDto.class);
    
    }

    @Override
    public StoresAtTargetDto storesAtTarget(String month) {
     
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/storesattarget?month=" + month;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(StoresAtTargetDto.class);
    
    }

    @Override
    public TopFourtyProducts getTop40Products() {
     
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/gettopfortyproducts";
        client = ClientBuilder.newClient();
        target = client.target(url);        
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(TopFourtyProducts.class);
    
    }

    @Override
    public LeastPerformingStoresDto getLeastPerforming(int interval) {
        LeastPerformingStoresDto dto = new LeastPerformingStoresDto();
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/leastperforming?interval=" + interval;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        
        try {
            dto = om.readValue(target.request().accept(MediaType.APPLICATION_JSON).get(String.class), LeastPerformingStoresDto.class);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dto;
    
    }

    @Override
    public ProductSales getProductSales() {
    
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/getproductsales";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(ProductSales.class);
    
    }

    @Override
    public StoreSalesDto storeDailySales(String branchId) {
      
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/storedailysales?branchId=" + branchId;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(StoreSalesDto.class);
    
    }
    
}
