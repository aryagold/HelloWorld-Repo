/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.Report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Model.Report.CustomerReports;
import za.co.vzap.Model.Report.LeastPerformingStores;
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
    public CustomerReports getCustomerReport() {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/getcustomerreport";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
   
        return response.readEntity(CustomerReports.class);
    
    }

    @Override
    public StoreSalesDto storeSalesByMonth(String branchId , String month) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/salesformonth?branchId=branchId&month=month";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
    
        return response.readEntity(StoreSalesDto.class);
    
    }

    @Override
    public TopEmployeesDto topSellingEmployees(String branchId) {
    
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/topsellingemployees?branchId=branchid";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(TopEmployeesDto.class);
    
    }

    @Override
    public StoresAtTargetDto storesAtTarget() {
     
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/storesattarget";
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
    public LeastPerformingStores getLeastPerforming() {
    
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/getleastperforming";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(LeastPerformingStores.class);
    
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
    public StoreSalesDto storeDailySales(String arg0) {
      
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/storedailysales";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(StoreSalesDto.class);
    
    }

    @Override
    public String downloadCurrentReport() {
     
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/report/downloadreport";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request().accept(MediaType.APPLICATION_JSON).get();
        
        return response.readEntity(String.class);
    
    }
    
}
