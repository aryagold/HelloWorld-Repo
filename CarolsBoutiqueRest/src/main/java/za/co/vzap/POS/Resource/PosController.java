package za.co.vzap.POS.Resource;

import java.util.List;
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
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.POS.Service.PosService;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.PaymentRepository;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;
import za.co.vzap.Interface.Service.IPosService;
import za.co.vzap.POS.Model.IbtDto;
import za.co.vzap.POS.Model.Refund;
import za.co.vzap.POS.Model.RefundItemDto;
import za.co.vzap.POS.Model.SaleDto;
import za.co.vzap.User.Repository.UserRepository;

@Path("pos")
public class PosController extends ControllerBase {
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
    private IRepository userRepository = new UserRepository();
    
    private IPosService posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);
    
    @POST
    @Path("sale")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSale(SaleDto dto) {
        dto = posService.addSale(dto);
        
        return Response.status(Response.Status.OK).entity(dto).build();
    }
    
    @GET
    @Path("sale")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSale(@QueryParam("id")String id) {
        return Response.status(Response.Status.OK).entity(posService.getSale(id)).build();
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
    public IbtDto requestIbt(IbtDto dto) throws Exception {
        dto = posService.addIbt(dto);
        
        return dto;
    }

    @POST
    @Path("ibt/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IbtDto updateIbt(IbtDto dto) throws Exception {
        return posService.updateIbt(dto);
    }
    
    @GET
    @Path("ibt")
    @Produces(MediaType.APPLICATION_JSON)
    public List<IbtDto> listIbt(@QueryParam("type")int type) {
        return posService.listIbt(this.userId, type);
    }
    
}
