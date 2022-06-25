/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.Product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Model.Inventory.Category;
import za.co.vzap.Model.Inventory.ProductDto;
import za.co.vzap.Model.Requests.AddProductRequest;

/**
 *
 * @author macpe
 */
public class ProductService implements IProductService {
    
    private ObjectMapper om;
    private String url;
    private Client client;
    private WebTarget target;
    private Response response;

    public ProductService() {

        this.om = new ObjectMapper();

    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Override
    public ProductDto addProduct(String name, double price, List<String> categoryIds) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/product/add";
        client =  ClientBuilder.newClient();
        target = client.target(url);
        
        AddProductRequest productRequest = new AddProductRequest(name, price, categoryIds);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(productRequest)));
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(ProductDto.class);
        
    }

    @Override
    public List<ProductDto> getAllProducts() {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/product/product";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request(MediaType.APPLICATION_JSON).get();
    
        return response.readEntity(List.class);
    
    }

    @Override
    public List<Category> getAllCategories() {
        
        url = "http://localhost:8080/rest/product/category";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request(MediaType.APPLICATION_JSON).get();
     
        return null;
    
    }

    @Override
    public String addCategory(Category category) {
        
        url = "http://localhost:8080/rest/product/category";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(category)));
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return response.readEntity(String.class);
    
    }
    
} 
