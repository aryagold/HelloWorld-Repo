package za.co.vzap.Arya.AryaStuff;

import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Customer.Repository.CustomerRepository;
import za.co.vzap.Customer.Repository.ReviewRepository;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;
import za.co.vzap.User.Repository.UserRepository;

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
    }
    
    public static void main(String[] args) {
        new SeeIfWork().run();
    }
    
    public void run() {
      
    }
    
}
