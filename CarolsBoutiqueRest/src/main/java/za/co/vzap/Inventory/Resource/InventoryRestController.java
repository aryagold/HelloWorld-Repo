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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Inventory.Service.InventoryService;
import za.co.vzap.Sale.Repository.SaleRepository;

@Path("inventory")
public class InventoryRestController {
    private IRepository inventoryRepository = new InventoryRepository();
    private IRepository inventoryControlRepository = new InventoryControlRepository();
    private IRepository productRepository = null;
    private IRepository productCategoryRepository = null;
    private IRepository sizeRepository = new SizeRepository();
    private IRepository saleRepository = new SaleRepository();
    private IRepository categoryRepository = new CategoryRepository();
    private IRepository branchRepository = new BranchRepository();
    
    private IInventoryService inventoryService = new InventoryService(productRepository, productCategoryRepository, inventoryControlRepository, inventoryRepository, sizeRepository, saleRepository, categoryRepository, branchRepository);
    
    
    @POST
    @Path("addproduct/{categoryid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(@PathParam("categoryid")List<String> categoryIds, Product product) {
        return Response.status(Response.Status.OK).entity(inventoryService.addProduct(product, categoryIds)).build();
    }
    
    @POST
    @Path("addcategory")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(Category category) {
        return Response.status(Response.Status.OK).entity(inventoryService.addCategory(category)).build();
    }
    
    @POST
    @Path("addinventorycontrol/{userid}/{barcode}/{quantity}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInventoryControl(@PathParam("userid")String userId, @PathParam("barcode")String barcode, @PathParam("quantity")int quantity) throws Exception {
        return Response.status(Response.Status.OK).entity(inventoryService.addInventoryControl(userId, barcode, quantity)).build();
    }
    
    @GET
    @Path("findproductwithproductid/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryDto> findProductWithProductId(@PathParam("productId")String productId) {
        List<InventoryDto> items = null; 
        
        try {
            items = inventoryService.findProductWithProductId(productId);
        } catch (Exception ex) {
            Logger.getLogger(InventoryRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    @GET
    @Path("findproductwithbarcode/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryDto> findProductWithBarcode(@PathParam("barcode")String barcode) {
        List<InventoryDto> items = null; 
        
        try {
            items = inventoryService.findProductWithBarcode(barcode);
        } catch (Exception ex) {
            Logger.getLogger(InventoryRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    
    
}
