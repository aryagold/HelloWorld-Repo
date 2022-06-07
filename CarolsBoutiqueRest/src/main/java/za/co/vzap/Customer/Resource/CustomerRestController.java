package za.co.vzap.Customer.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Customer.Repository.CustomerRepository;
import za.co.vzap.Customer.Repository.ReviewRepository;
import za.co.vzap.Customer.Service.CustomerService;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.ICustomerService;
import za.co.vzap.Report.Model.ItemAmount;
import za.co.vzap.Report.Model.TopAchievingStores;

@Path("customer")
public class CustomerRestController {
    private IRepository reviewRepository = new ReviewRepository();
    private IRepository customerRepository = new CustomerRepository();
    
    private ICustomerService customerService = new CustomerService(reviewRepository, customerRepository);
    
    @POST
    @Path("addreview")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReview(Review review) {
        return Response.status(Response.Status.OK).entity(customerService.addReview(review)).build();
    }
    
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        
        return Response.status(Response.Status.OK).entity(customerService.addCustomer(customer)).build();
    }
    
    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public TopAchievingStores test() {
        TopAchievingStores stores = new TopAchievingStores();
        stores.storeSales.add(new ItemAmount("store1", 1000000));
        return stores;
    }
    
    
}
