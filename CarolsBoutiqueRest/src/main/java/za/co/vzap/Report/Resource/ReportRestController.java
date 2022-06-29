package za.co.vzap.Report.Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Path("customerreport")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerReport(@QueryParam("month")String month, @QueryParam("resultAmount")int resultAmount) {
        return Response.status(Response.Status.OK).entity(reportService.getCustomerReport(month, resultAmount)).build();
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
    public Response getStoresAtTarget(@QueryParam("month")String month) {
        return Response.status(Response.Status.OK).entity(reportService.storesAtTarget(month)).build();
    }
    
    @GET
    @Path("gettopfortyproducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopFourtyProducts() {
        return Response.status(Response.Status.OK).entity(reportService.getTop40Products()).build();
    }
    
    @GET
    @Path("leastperforming")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLeastPerforming(@QueryParam("interval")int interval) {
        return Response.status(Response.Status.OK).entity(reportService.getLeastPerforming(interval)).build();
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
    @Path("topachievingstores/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadTopStoresReport() {
       
        return Response
                .status(Response.Status.OK)
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .entity(reportService.downloadTopStoresReport())
                .build();
    }
    
    @GET
    @Path("customerreport/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadCustomerReport(@QueryParam("month")String month, @QueryParam("resultAmount")int resultAmount) {

        return Response
                .status(Response.Status.OK)
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .entity(reportService.downloadCustomerReport(month, resultAmount))
                .build();
    }
    
    @GET
    @Path("storemonthlysales/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadMonthlySalesReport(@QueryParam("branchId")String branchId, @QueryParam("month")String month) {

        return Response
                .status(Response.Status.OK)
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .entity(reportService.downloadMonthSalesReport(branchId, month))
                .build();
    }
    
    @GET
    @Path("topemployees/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadTopEmployeesReport(@QueryParam("branchId")String branchId) {

        return Response
                .status(Response.Status.OK)
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .entity(reportService.downloadTopEmployeesReport(branchId))
                .build();
    }
    
    @GET
    @Path("storesattarget/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadStoresAtTargetReport(@QueryParam("month")String month) {

        return Response
                .status(Response.Status.OK)
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .entity(reportService.storesAtTarget(month))
                .build();
    }
    
    @GET
    @Path("leastperforming/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadLeastPerformingReport(@QueryParam("interval")int interval) {

        return Response
                .status(Response.Status.OK)
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .entity(reportService.getLeastPerforming(interval))
                .build();
    }
    
    @GET
    @Path("storedailysales/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadDailySalesReport(@QueryParam("branchId")String branchId) {

        return Response
                .status(Response.Status.OK)
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .entity(reportService.downloadDailySalesReport(branchId))
                .build();
    }
}
