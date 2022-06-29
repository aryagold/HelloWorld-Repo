/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.Inventory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Model.Inventory.InventoryControlDto;
import za.co.vzap.Model.Inventory.InventoryDto;
import za.co.vzap.Model.Requests.AddInventoryRequest;
import za.co.vzap.Model.Requests.CaptureInventoryRequest;

/**
 *
 * @author macpe
 */
public class InventoryService implements IInventoryService{

    private ObjectMapper om;
    private String url;
    private Client client;
    private WebTarget target;
    private Response response;

    public InventoryService() {

        this.om = new ObjectMapper();

    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
    
    @Override
    public List<InventoryDto> getBranchInventory(String userID) {// should userID be passed in as a parmeter if its in the header?
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/inventory";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        List<InventoryDto> list = null;
        try {
            
            list = om.readValue(target.request().accept(MediaType.APPLICATION_JSON).get(String.class),List.class);
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(InventoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }

    @Override
    public InventoryControlDto captureInventory(String userId, String barcode ,int quantity) throws Exception {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/inventory/capture";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        CaptureInventoryRequest captureInventoryRequest = new CaptureInventoryRequest(userId, barcode, quantity);
        
        response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(captureInventoryRequest)));
        
        return response.readEntity(InventoryControlDto.class);
        
    }

    @Override
    public InventoryDto addInventory(String userID, String productId, int sizeId, String barcode) {
      
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/inventory/add";
        client = ClientBuilder.newClient();
        target = client.target(url);

        AddInventoryRequest addInventoryRequest = new AddInventoryRequest(userID, productId, sizeId, barcode);

        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(addInventoryRequest)));
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(InventoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(InventoryDto.class);
    
    }

    @Override
    public InventoryDto getItem(String barcode) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/inventory/getitem?barcode=" + barcode;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        InventoryDto invenDto = null;
        
        try {
            
             invenDto = om.readValue(target.request().accept(MediaType.APPLICATION_JSON).get(String.class), InventoryDto.class);
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(InventoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return invenDto;
        
    }

    @Override
    public List<InventoryDto> findInventory(String searchTerm) throws Exception {
       
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/inventory/find?searchTerm=" + searchTerm;
        client = ClientBuilder.newClient();
        target = client.target(url);

        List<InventoryDto> invenDtos = null;

        try {

            invenDtos = Arrays.asList(om.readValue(target.request().accept(MediaType.APPLICATION_JSON).get(String.class), InventoryDto[].class));

        } catch (JsonProcessingException ex) {
            Logger.getLogger(InventoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return invenDtos;
        
    }
    
}
