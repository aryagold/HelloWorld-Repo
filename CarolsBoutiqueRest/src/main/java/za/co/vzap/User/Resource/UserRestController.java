package za.co.vzap.User.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;
import za.co.vzap.User.Service.UserService;

@Path("user")
public class UserRestController {
    private IRepository userRepository = new UserRepository();
    private IRepository branchReposiotry = new BranchRepository();
    
    private IUserService userService = new UserService(userRepository, branchReposiotry);
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) throws Exception {
        return Response.status(Response.Status.OK).entity(userService.login(user)).build();
    }
    
    @POST
    @Path("updateToTeller/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateToTeller(@PathParam("userId")String userId) throws Exception {
        return Response.status(Response.Status.OK).entity(userService.updateToTeller(userId)).build();
    }
    
    @POST
    @Path("addBranch")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBranch(Branch branch) throws Exception {
        return Response.status(Response.Status.OK).entity(userService.addBranch(branch)).build();
    }
}
