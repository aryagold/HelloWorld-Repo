/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.Inventory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        
        url = "http://localhost:8080/rest/inventory";
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
        
        url = "http://localhost:8080/rest/inventory/capture";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        CaptureInventoryRequest request = new CaptureInventoryRequest(userId, barcode, quantity);
        
        response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(request)));
        
        return response.readEntity(InventoryControlDto.class);
        
    }

    @Override
    public InventoryDto addInventory(String userID, String productId, int sizeId, String barcode) {
      
        url = "http://localhost:8080/rest/inventory/add";
        client = ClientBuilder.newClient();
        target = client.target(url);

        AddInventoryRequest request = new AddInventoryRequest(userID, productId, sizeId, barcode);

        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(request)));
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(InventoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(InventoryDto.class);
    
    }

    @Override
    public List<InventoryDto> findStockWithProductId(String productId) throws Exception {
        
        Map map = new HashMap<>();
        map.put("productId", productId);
        
        url = "http://localhost:8080/rest/inventory/findstockwithproductid/{productId}";
        client = ClientBuilder.newClient();
        target = client.target(url).resolveTemplates(map);
        
        List<InventoryDto> list = om.readValue(target.request().accept(MediaType.APPLICATION_JSON).get(String.class), List.class);
        
        return list;
        
    }

    @Override
    public List<InventoryDto> findStockWithBarcode(String barcode) throws Exception {
        
        Map map = new HashMap<>();
        map.put("barcode", barcode );
        
        url = "http://localhost:8080/rest/inventory/findstockwithbarcode";
        client = ClientBuilder.newClient();
        target = client.target(url).resolveTemplates(map);
        
        List<InventoryDto> list = om.readValue( target.request().accept(MediaType.APPLICATION_JSON).get(String.class) , List.class);
        
        return list;
        
    }
    
}
