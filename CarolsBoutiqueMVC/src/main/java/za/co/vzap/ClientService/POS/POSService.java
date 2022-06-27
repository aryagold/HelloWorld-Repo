/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.POS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Model.Sale.IBTStatusEnum;
import za.co.vzap.Model.Sale.IbtDto;
import za.co.vzap.Model.Sale.RefundDto;
import za.co.vzap.Model.Sale.SaleDto;

/**
 *
 * @author macpe
 */
public class POSService implements IPOSService {
    private ObjectMapper om;
    private String url;
    private Client client;
    private WebTarget target;
    private Response response;

    public POSService() {

        this.om = new ObjectMapper();

    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Override
    public SaleDto addSale(SaleDto dto) {

        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/sale";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(dto)));
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(POSService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(SaleDto.class);
        
    }

    @Override
    public SaleDto getSale(String id) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/sale?id=id";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        SaleDto dto = null;
        
        try {
            
            dto = om.readValue( target.request().accept(MediaType.APPLICATION_JSON).get(String.class) , SaleDto.class);
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(POSService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dto ;
        
    }

    @Override
    public RefundDto addRefund(RefundDto dto) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/refund";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        try {
            
            response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(dto)));
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(POSService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.readEntity(RefundDto.class);
        
    }

    @Override
    public RefundDto getRefund(int id) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/refund?id=id";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        RefundDto refundDto = null;
        
        try {
            
            refundDto = om.readValue( target.request().accept(MediaType.APPLICATION_JSON).get(String.class) , RefundDto.class);
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(POSService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return refundDto;
        
    }

    @Override
    public IbtDto addIbt(IbtDto dto) {
       
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/ibt";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(dto));
        
        return response.readEntity(IbtDto.class);
       
    }

//    @Override
//    public IbtDto updateIbt(IbtDto dto) {
//        
//        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/ibt/update";
//        client = ClientBuilder.newClient();
//        target = client.target(url);
//        
//        response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(dto));
//        
//        return response.readEntity(IbtDto.class);
//       
//    }
    
    public void approveIbt(int ibtId) {
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/ibt/status";
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        IbtDto dto = new IbtDto();
        dto.Id = ibtId;
        dto.status = IBTStatusEnum.APPROVED;

        target.request(MediaType.APPLICATION_JSON).post(Entity.json(dto));
    }

    @Override
    public List<IbtDto> listIbt(String userId, int type) {
        
        url = "http://localhost:8080/CarolsBoutiqueRest/rest/pos/ibt?type=" + type;
        client = ClientBuilder.newClient();
        target = client.target(url);
        
        List<IbtDto> dtos = null;
        
        try {
            
            String response = target.request().header("userId", userId).accept(MediaType.APPLICATION_JSON).get(String.class);
            dtos = Arrays.asList(om.readValue(response, IbtDto[].class));
        
        } catch (JsonProcessingException ex) {
            Logger.getLogger(POSService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dtos;
        
    }

   
    
}
