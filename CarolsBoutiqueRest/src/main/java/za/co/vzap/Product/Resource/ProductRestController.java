package za.co.vzap.Product.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.ProductCategoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Resource.AddProductRequest;
import za.co.vzap.Product.Service.ProductService;

@Path("product")
public class ProductRestController {
    private IRepository productCategoryRepository = new ProductCategoryRepository();
    private IRepository categoryRepository = new CategoryRepository();
    private IRepository productRepository = new ProductRepository();

    private IProductService productService = new ProductService(productRepository, productCategoryRepository, categoryRepository);

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(AddProductRequest request) {
        return Response.status(Response.Status.OK).entity(productService.addProduct(request.name, request.price, request.categoryIds)).build();
    }

    @GET
    @Path("product")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        return Response.status(Response.Status.OK).entity(productService.getAllProducts()).build();
    }

    @POST
    @Path("category")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(Category category) {
        return Response.status(Response.Status.OK).entity(productService.addCategory(category)).build();
    }

    @GET
    @Path("category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        return Response.status(Response.Status.OK).entity(productService.getAllCategories()).build();
    }
}
