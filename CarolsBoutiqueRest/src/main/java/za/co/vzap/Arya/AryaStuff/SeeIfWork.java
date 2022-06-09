package za.co.vzap.Arya.AryaStuff;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Customer.Repository.CustomerRepository;
import za.co.vzap.Customer.Repository.ReviewRepository;
import za.co.vzap.Customer.Service.CustomerService;
import za.co.vzap.Interface.Service.ICustomerService;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductCategoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Inventory.Service.InventoryService;
import za.co.vzap.POS.Service.POSService;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IBTStatusEnum;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.PaymentTypeEnum;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.PaymentRepository;
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
    IRepository productCategoryRepository = null;
    IRepository sizeRepository = null;
    IRepository paymentRepository = null;
    
    
    ICustomerService customerService = null;
    IUserService userService = null;
    IInventoryService inventoryService = null;
    IPOSService posService = null;
    
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
        productCategoryRepository = new ProductCategoryRepository();
        sizeRepository = new SizeRepository();
        paymentRepository = new PaymentRepository();
        
        customerService = new CustomerService(reviewRepository, customerRepository);
        userService = new UserService(userRepository, branchRepository);
        inventoryService = new InventoryService(productRepository, productCategoryRepository, inventoryControlRepository, inventoryRepository, sizeRepository, saleRepository, categoryRepository, branchRepository);
        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository);
        
    }
    
    public static void main(String[] args) {
        new SeeIfWork().run();
    }
    
    public void run() {
        
//        boolean reserved = posService.reserveSale("SL001");
//        
//        System.out.println("The sale is reserved is: " + reserved);
        
//        boolean voided = posService.voidSale("SL001");
//        
//        System.out.println("The sale was voided is :" + voided);
        
//          int id = paymentRepository.add(new Payment(PaymentTypeEnum.CASH, "", true));
//          
//          System.out.println("The payment ID is: " + id);
        
//        User user = userService.login(new User("US001", "password"));
//        
//        System.out.println(user.toString());
        
//        int id = refundRepository.add(new Refund("SL001", Timestamp.valueOf(LocalDateTime.now())));
//        
//        System.out.println("The refund Id is: " + id);
        
//        int id = refundItemRepository.add(new RefundItem(2, 1));
//        
//        System.out.println("The refundItem ID is: " + id);
        
//        int id = ibtRepository.add(new IBT("BR001", "BR002", "PR001", 1, "0798036817", IBTStatusEnum.REQUESTED));
//        
//        System.out.println("The ibt ID is: " + id);
        
//        IBT ibt = (IBT) ibtRepository.getById(1);
//        
//        System.out.println("The ibt returned is: " + ibt.toString());
//        
//        int id = inventoryRepository.add(new Inventory("BR001", 2, "PR021", "00212", 10));
//        // branch id, size id, product id, barcode, quantity
//        
//        System.out.println("The inventory ID is: " + id);
        
        
        
//        int id = inventoryControlRepository.add(new InventoryControl("US001", "PR001", Timestamp.valueOf(LocalDateTime.now()), 3, 2, 5, false));
//        System.out.println("Inventory Control ID: " + id);
        
//        int id = sizeRepository.add(new Size("10"));
//        System.out.println("Size ID is: " + id);
        
//      int id = customerRepository.add(new Customer("aryagoldtestdb", "0798036817"));
//        
//      System.out.println("The customer ID is: " + id);
        
        
//      String id = userRepository.add2(new User("Kendall", "employee@gmail.com", "BR007", "password", RoleEnum.TELLER));
//        
//      System.out.println("The user ID is: " + id);

//        boolean updated = userService.updateToTeller("US002");
//        
//        System.out.println("Was it updated? " + updated);
        
//        String branchId = userService.addBranch(new Branch("QueenstownCarolsBoutique", 55000.00, 2500.00));
//        
//        System.out.println("The new branch ID added from the service is: " + branchId);

//          User user = userService.login(new User("US001", "password"));
//          
//          System.out.println("The returned user is: " + user.toString());
      
        
//      String id = categoryRepository.add2(new Category("Womens Athleisure"));
//      System.out.println("The category ID is: " + id);
        

//        String id = branchRepository.add2(new Branch("ParktownCarolsBoutique", 5435.5, 434.5));
//        System.out.println(id);

//      int id = reviewRepository.add(new Review("good service", 9, "BR001"));
//      System.out.println("Review ID " + id);

//      String id = saleRepository.add2(new Sale("US001", "aryagoldridge@gmail.com", Timestamp.valueOf(LocalDateTime.now()), null, SaleStatusEnum.COMPLETED));
//        
//      System.out.println("The sale ID is: " + id);

//      String id = productRepository.add2(new Product("black jacket", 699.99));
//        
//      System.out.println("The product ID is: " + id);

//        String response = inventoryService.addProduct(new Product("", 500.00), Arrays.asList("CA005"));
//        
//        System.out.println(response);

//        String response = inventoryService.addCategory(new Category("Mens Shoes"));
//        System.out.println(response);

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
