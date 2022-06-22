/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.User;

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
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.Model.Branch.Branch;
import za.co.vzap.Model.User.User;
import za.co.vzap.Model.User.UserDto;

/**
 *
 * @author macpe
 */
public class UserService implements IUserService{
    
    private ObjectMapper om;
    private String url;
    private Client client;
    private WebTarget target;
    private Response response;
    
    public UserService (){
        
        this.om = new ObjectMapper();
        client = ClientBuilder.newClient();
        
    }

    @Override
    public boolean updateToTeller(String userID) {// the end point was changed to not take any path params.
        
        url = "http://localhost:8080/rest/user/updatetoteller";
  
        target = client.target(url);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(userID)));
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(Boolean.class);
    
    }

    @Override
    public String addBranch(Branch branch) {
        
        url = "http://localhost:8080/rest/user/addbranch";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(branch)));
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(String.class);
    
    }

    @Override
    public UserDto login(User user) {
        System.out.println("here2");
        url = "http://localhost:8080/rest/user/login";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        UserDto dto = null;
       
        try {
            
            response = target.request().post(Entity.json(stringJson(user)));

            
            dto = om.readValue(response.readEntity(String.class), UserDto.class);
             
            System.out.println("response : "+response);
            
            dto = om.readValue(response.readEntity(String.class), UserDto.class);
            System.out.println("Response " + response);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return dto;
        
    }
    
    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Override
    public List<Branch> getAllBranches() {
      
        url = "http://localhost:8080/rest/user/branches";
        client = ClientBuilder.newClient();
        target = client.target(url);

        try {

            List<Branch> branches = om.readValue( target.request().accept(MediaType.APPLICATION_JSON).get(String.class), List.class);
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response.readEntity(List.class);
        
    }
    
}
