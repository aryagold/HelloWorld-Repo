package za.co.vzap.Inventory.Resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductCodeRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Inventory.Service.InventoryService;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.SaleRepository;

@Path("inventory")
public class InventoryRestController {
    private IRepository inventoryRepository = new InventoryRepository();
    private IRepository inventoryControlRepository = new InventoryControlRepository();
    private IRepository productRepository = null;
    private IRepository productCategoryRepository = null;
    private IRepository sizeRepository = new SizeRepository();
    private IRepository productCodeRepository = new ProductCodeRepository();
    private IRepository ibtRepository = new IBTRepository();
    private IRepository saleRepository = new SaleRepository();
    
    private IInventoryService inventoryService = new InventoryService(productRepository, productCategoryRepository, inventoryControlRepository, inventoryRepository, sizeRepository, productCodeRepository, ibtRepository, saleRepository);
    
//    @POST
//    @Path("addInventoryControl")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public int addInventoryControl(InventoryControl inventoryControl, Inventory inventory) throws Exception {
//        return inventoryService.addInventoryControl(inventoryControl, inventory);
//    }
    
    @GET
    @Path("findProduct/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Inventory> findProduct(@PathParam("productId")String productId) {
        return inventoryService.findProduct(productId);
    }
    
    @POST
    @Path("requestIBT")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void requestIBT(IBT ibt) throws Exception {
        inventoryService.requestIBT(ibt);
    }
    
    @POST
    @Path("acceptIBT")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void acceptIBT(IBT ibt) throws Exception {
        inventoryService.acceptIBT(ibt);
    }
    
    @POST
    @Path("declineIBT")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void declineIBT(IBT ibt) throws Exception {
        inventoryService.declineIBT(ibt);
    }
    
    @POST
    @Path("IBTReceived")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void IBTReceived(IBT ibt) throws Exception {
        inventoryService.IBTReceived(ibt);
    }
    
}
