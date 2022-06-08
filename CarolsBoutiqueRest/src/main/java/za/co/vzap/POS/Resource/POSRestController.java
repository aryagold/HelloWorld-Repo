package za.co.vzap.POS.Resource;

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
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.POS.Service.POSService;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleLineItemDto;
import za.co.vzap.Sale.Repository.PaymentRepository;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;

@Path("pos")
public class POSRestController {
    private IRepository productRepository = new ProductRepository();
    private IRepository saleRepository = new SaleRepository();
    private IRepository refundRepository = new RefundRepository();
    private IRepository refundItemRepository = new RefundItemRepository();
    private IRepository inventoryRepository = new InventoryRepository();
    private IRepository saleLineItemRepository = new SaleLineItemRepository();
    private IRepository paymentRepository = new PaymentRepository();
    private IRepository sizeRepository = new SizeRepository();
    
    private IPOSService posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository);
    
    @POST
    @Path("addsale")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSale(Sale sale) {
        String id = posService.addSale(sale);
        
        return Response.status(Response.Status.OK).entity(id).build();
    }
    
    @POST
    @Path("addsalelineitem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSaleLineItem(SaleLineItemDto dto) {
        try {
            dto = posService.addSaleLineItem(dto);
        } catch (Exception ex) {
            Logger.getLogger(POSRestController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
        
        return Response.status(Response.Status.OK).entity(dto).build();
    }
    
    @GET
    @Path("{saleId}/getsalelineitems")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSaleLineItems(@PathParam("saleId")String saleId) {
        List<SaleLineItemDto> dtos = posService.getSaleLineItems(saleId);
        
        return Response.status(Response.Status.OK).entity(dtos).build();
    }
    
    @POST
    @Path("voidSale")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response voidSale(Sale sale) {
        boolean voided = posService.voidSale(sale);
        
        return Response.status(Response.Status.OK).entity(voided).build();
    }
    
    @POST
    @Path("deletesalelineitem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSaleLineItem(@PathParam("id")Integer id) {
        boolean deleted = posService.deleteSaleLineItem(id);
        
        return Response.status(Response.Status.OK).entity(deleted).build();
    }
    
    @POST
    @Path("completeSale")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmSale(Sale sale) {
        boolean updated = posService.completeSale(sale);
        
        return Response.status(Response.Status.OK).entity(updated).build();
    }
    
    }
