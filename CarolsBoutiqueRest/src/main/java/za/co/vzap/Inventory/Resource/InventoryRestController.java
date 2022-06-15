package za.co.vzap.Inventory.Resource;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Common.Resource.ControllerBase;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Inventory.Service.InventoryService;
import za.co.vzap.User.Repository.UserRepository;

@Path("inventory")
public class InventoryRestController extends ControllerBase {
    private IRepository inventoryRepository = new InventoryRepository();
    private IRepository inventoryControlRepository = new InventoryControlRepository();
    private IRepository productRepository = new ProductRepository();
    private IRepository sizeRepository = new SizeRepository();
    private IRepository branchRepository = new BranchRepository();
    private IRepository userRepository = new UserRepository();
    
    private IInventoryService inventoryService = new InventoryService(productRepository, inventoryControlRepository, inventoryRepository, sizeRepository, branchRepository, userRepository);
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryDto> getBranchInventory() {
        return inventoryService.getBranchInventory(userId);
    }
    
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInventory(AddInventoryRequest request) {
        return Response.status(Response.Status.OK).entity(inventoryService.addInventory(request.userId, request.productId, request.sizeId, request.barcode)).build();
    }
    
    @POST
    @Path("capture")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response captureInventory(CaptureInventoryRequest request) {
        try {
            return Response.status(Response.Status.OK).entity(inventoryService.captureInventory(request.userId, request.barcode, request.quantity)).build();
        } catch (Exception ex) {
            Logger.getLogger(InventoryRestController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
            
        }
    }
    
    @GET
    @Path("findstockwithproductid/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryDto> findProductWithProductId(@PathParam("productId")String productId) {
        List<InventoryDto> items = null; 
        
        try {
            items = inventoryService.findStockWithProductId(productId);
        } catch (Exception ex) {
            Logger.getLogger(InventoryRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    @GET
    @Path("findstockwithbarcode/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryDto> findProductWithBarcode(@PathParam("barcode")String barcode) {
        List<InventoryDto> items = null; 
        
        try {
            items = inventoryService.findStockWithBarcode(barcode);
        } catch (Exception ex) {
            Logger.getLogger(InventoryRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    @GET
    @Path("getitem")
    @Produces(MediaType.APPLICATION_JSON)
    public InventoryDto getItem(@QueryParam("barcode")String barcode) {
        return inventoryService.getItem(barcode);
    } 
}
