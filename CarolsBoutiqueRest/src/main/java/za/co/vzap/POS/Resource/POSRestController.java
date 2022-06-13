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
import za.co.vzap.Sale.Model.IbtDto;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItemDto;
import za.co.vzap.Sale.Model.Sale;
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
    @Path("sale")
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
    @Path("sale/{saleid}/complete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmSale(@PathParam("saleid")String saleId, Payment payment) {
        int paymentId = posService.completeSale(payment, saleId);
        
        return Response.status(Response.Status.OK).entity(paymentId).build();
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
    @Path("ibt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IbtDto requestIbt(IbtRequest request) throws Exception {
        IbtDto dto = posService.requestIbt(request.inventoryIdFrom, request.branchIdTo, request.quantity, request.phoneNumber, request.emailAddress);
        
        return dto;
    }

    @POST
    @Path("ibt/{id}/approve")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IbtDto acceptIbt(@PathParam("id")int id) throws Exception {
        return posService.approveIbt(id);
    }

    @POST
    @Path("ibt/{id}/decline")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IbtDto declineIBT(@PathParam("id") int id) throws Exception {
        return posService.declineIbt(id);
    }

    @POST
    @Path("ibt/{id}/received")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IbtDto IBTReceived(@PathParam("id") int id) throws Exception {
        return posService.ibtReceived(id);
    }
    
//    @POST
//    @Path("ibt/{id}/pay")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void payIBT(InventoryDto dto, @PathParam("id")int id, Sale sale, Payment payment) throws Exception {
//        posService.payIBT(dto, id, sale, payment);
//    }
    
}
