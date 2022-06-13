package za.co.vzap.Report.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Report.Service.ReportService;

@Path("report")
public class ReportRestController {
    
    private IReportService reportService = new ReportService();
    
    @GET
    @Path("topachievingstores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopAchievingStores() {
        return Response.status(Response.Status.OK).entity(reportService.topAchievingStores()).build();
    }
    
    @GET
    @Path("getcustomerreport")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerReport() {
        return Response.status(Response.Status.OK).entity(reportService.getCustomerReport()).build();
    }
    
    @GET
    @Path("salesformonth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesForMonth(@QueryParam("branchId")String branchId, @QueryParam("month")String month) {
        return Response.status(Response.Status.OK).entity(reportService.storeSalesByMonth(branchId, month)).build();
    }
    
    @GET
    @Path("topsellingemployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopEmployees(@QueryParam("branchId")String branchId) {
        return Response.status(Response.Status.OK).entity(reportService.topSellingEmployees(branchId)).build();
    }
    
    @GET
    @Path("storesattarget")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoresAtTarget() {
        return Response.status(Response.Status.OK).entity(reportService.storesAtTarget()).build();
    }
    
    @GET
    @Path("gettopfortyproducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopFourtyProducts() {
        return Response.status(Response.Status.OK).entity(reportService.getTop40Products()).build();
    }
    
    @GET
    @Path("getleastperforming")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLeastPerforming() {
        return Response.status(Response.Status.OK).entity(reportService.getLeastPerforming()).build();
    }
    
    @GET
    @Path("getproductsales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductSales() {
        return Response.status(Response.Status.OK).entity(reportService.getProductSales()).build();
    }
    
    @GET
    @Path("storedailysales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDailySales(@QueryParam("branchId")String branchId) {
        return Response.status(Response.Status.OK).entity(reportService.storeDailySales(branchId)).build();
    }
    
    @GET
    @Path("downloadreport")
    @Produces(MediaType.APPLICATION_JSON)
    public Response downloadReport() {
        return Response.status(Response.Status.OK).entity(reportService.downloadCurrentReport()).build();
    }
}
