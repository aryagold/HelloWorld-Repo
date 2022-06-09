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
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.POS.Service.POSService;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItemDto;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleLineItemDto;
import za.co.vzap.Sale.Repository.IBTRepository;
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
    private IRepository ibtRepository = new IBTRepository();
    private IRepository branchRepository = new BranchRepository();
    
    private IPOSService posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository);
    
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
    @Path("voidsale/{saleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response voidSale(@PathParam("saleId")String saleId) {
        
        return Response.status(Response.Status.OK).entity(posService.voidSale(saleId)).build();
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
    @Path("completesale/{saleid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmSale(@PathParam("saleid")String saleId, Payment payment) {
        boolean updated = posService.completeSale(payment, saleId);
        
        return Response.status(Response.Status.OK).entity(updated).build();
    }
    
    @POST
    @Path("addrefund")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRefund(Refund refund) {

        return Response.status(Response.Status.OK).entity(posService.addRefund(refund)).build();
    }
    
    @POST
    @Path("addrefunditem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRefundItem(RefundItemDto dto) {

        return Response.status(Response.Status.OK).entity(posService.addRefundItem(dto)).build();
    }
    
    @POST
    @Path("reservesale/{saleid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reserveSale(@PathParam("saleid")String saleId) {

        return Response.status(Response.Status.OK).entity(posService.reserveSale(saleId)).build();
    }
    
    @POST
    @Path("requestibt/{phonenumber}/{branchidto}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void requestIBT(InventoryDto dto, @PathParam("phonenumber")String phoneNumber, @PathParam("branchidto")String branchIdTo) throws Exception {
        posService.requestIBT(dto, phoneNumber, branchIdTo);
    }

    @POST
    @Path("acceptibt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void acceptIBT(@PathParam("id")int id) throws Exception {
        posService.acceptIBT(id);
    }

    @POST
    @Path("declineibt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void declineIBT(@PathParam("id") int id) throws Exception {
        posService.declineIBT(id);
    }

    @POST
    @Path("ibtreceived/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void IBTReceived(@PathParam("id") int id) throws Exception {
        posService.IBTReceived(id);
    }
    
    @POST
    @Path("payibt/{id}/{email}/{cardNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void payIBT(List<InventoryDto> dtos, @PathParam("id")int id, @PathParam("email")String email, @PathParam("cardNumber")String cardNumber) throws Exception {
        posService.payIBT(dtos, id, email, cardNumber);
    }
    
}
