package za.co.vzap.Arya.AryaStuff;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Customer.Repository.CustomerRepository;
import za.co.vzap.Customer.Repository.ReviewRepository;
import za.co.vzap.Customer.Service.CustomerService;
import za.co.vzap.Interface.Service.ICustomerService;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;
import za.co.vzap.User.Service.UserService;

public class SeeIfWork {
    IRepository customerRepository = null;
    IRepository productRepository = null;
    IRepository saleRepository = null;
    IRepository productSaleRepository = null;
    IRepository userRepository = null;
    IRepository inventoryRepository = null;
    IRepository categoryRepository = null;
    IRepository ibtRepository = null;
    IRepository refundItemRepository = null;
    IRepository refundRepository = null;
    IRepository saleLineItemRepository = null;
    IRepository branchRepository = null;
    IRepository reviewRepository = null;
    IRepository inventoryControlRepository = null;
    
    ICustomerService customerService = null;
    IUserService userService = null;
    
    public SeeIfWork() {
        customerRepository = new CustomerRepository();
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        userRepository = new UserRepository();
        inventoryRepository = new InventoryRepository();
        categoryRepository = new CategoryRepository();
        ibtRepository = new IBTRepository();
        refundItemRepository = new RefundItemRepository();
        refundRepository = new RefundRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        branchRepository = new BranchRepository();
        reviewRepository = new ReviewRepository();
        inventoryControlRepository = new InventoryControlRepository();
        
        customerService = new CustomerService(reviewRepository, customerRepository);
        userService = new UserService(userRepository, branchRepository);
    }
    
    public static void main(String[] args) {
        new SeeIfWork().run();
    }
    
    public void run() {
        
//      int id = customerRepository.add(new Customer("aryagold", "0798036817"));
//        
//      System.out.println("The customer ID is: " + id);
        
        
//      String id = userRepository.add2(new User("arya", "aryasemail", "BR001", "password", RoleEnum.MANAGER));
//        
//      System.out.println("The user ID is: " + id);

//        boolean updated = userService.updateToTeller("US002");
//        
//        System.out.println("Was it updated? " + updated);
        
//        String branchId = userService.addBranch(new Branch("SandtonCarol'sBoutique", 6546.654, 434.54));
//        
//        System.out.println("The new branch ID added from the service is: " + branchId);

//          User user = userService.login(new User("US001", "password"));
//          
//          System.out.println("The returned user is: " + user.toString());
      
        
//      String id = categoryRepository.add2(new Category("Womens athleisure"));
//      System.out.println("The category ID is: " + id);
        

//        String id = branchRepository.add2(new Branch("ParktownCarolsBoutique", 5435.5, 434.5));
//        System.out.println(id);

//      int id = reviewRepository.add(new Review("good service", 9, "BR001"));
//      System.out.println("Review ID " + id);

      String id = saleRepository.add2(new Sale("US001", "aryagoldridge@gmail.com", Timestamp.valueOf(LocalDateTime.now()), null, SaleStatusEnum.COMPLETED));
        
      System.out.println("The sale ID is: " + id);

//      String id = productRepository.add2(new Product("black jacket", 699.99));
//        
//      System.out.println("The product ID is: " + id);

//        Customer customer = new Customer("testservice", "testservice");
//
//        int id = customerService.addCustomer(customer);
//        
//        System.out.println("Customer's ID is: " + id);

//        Review review = new Review("friendly", 9, "BR001");
//        int id = customerService.addReview(review);
//        System.out.println("Review ID is: " + id);
    
    }
}
