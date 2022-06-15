/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.Customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Service.ICustomerService;
import za.co.vzap.Model.Customer.Customer;
import za.co.vzap.Model.Customer.Review;

/**
 *
 * @author macpe
 */
public class CustomerService implements ICustomerService {
    private ObjectMapper om;
    private String url ;
    private Client client;
    private WebTarget target;
    private Response response;
    
    public CustomerService(){
        om = new ObjectMapper();
    }

    @Override
    public int addCustomer(Customer customer) {
        
        url = "http://localhost:8080/rest/customer/add" ;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(customer)));
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return response.readEntity(Integer.class);  
        
    }

    @Override
    public int addReview(Review review) {
      
        url = "http://localhost:8080/rest/customer/addreview";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(review)));
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(Integer.class);
        
    }
    
    private String stringJson(Object o) throws JsonProcessingException {
        return om.writeValueAsString(o);
    }   
}
