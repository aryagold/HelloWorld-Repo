 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.POS.Model.Payment;
import za.co.vzap.POS.Model.PaymentTypeEnum;
import za.co.vzap.POS.Model.Refund;
import za.co.vzap.POS.Model.RefundItem;
import za.co.vzap.POS.Model.RefundStatusEnum;
import za.co.vzap.POS.Model.Sale;
import za.co.vzap.POS.Model.SaleStatusEnum;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

/**
 *
 * @author macpe
 */
public class RefundItemRepositoryTest {// throws error in the repository
    
    private IRepository refundItemRepository;
    private IRepository inventoryDB;
    private IRepository refundDB;
    private IRepository branchDB;
    private IRepository productDB;
    private IRepository sizeDB;
    private IRepository paymentDB;
    private IRepository saleDB;
    private IRepository userDB;
    
    @Test
    public void testAdd() {//done
        refundItemRepository = new RefundItemRepository();
        inventoryDB = new InventoryRepository();
        refundDB = new RefundRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        sizeDB = new SizeRepository();
        paymentDB = new PaymentRepository();
        saleDB = new SaleRepository();
        userDB = new UserRepository();
      
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        
        Product product = new Product("TestProduct1", 10);
        String productID = productDB.add2(product);
        
        Size size = new Size("XXL");
        int sizeID = sizeDB.add(size);
        
        Inventory inven = new Inventory(branchID, sizeID, productID,"TestBarcode", 10);
        int invenID = inventoryDB.add(inven);
        
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        Refund refund = new Refund( saleID ,Timestamp.valueOf(LocalDateTime.now()),RefundStatusEnum.NEW);
        int refundID = refundDB.add(refund);
        
        RefundItem refundItem = new RefundItem(invenID,refundID);
        
        Integer result = refundItemRepository.add(refundItem);
       
        assertEquals(Integer.class, result.getClass());
  
    }

    
    @Test
    public void testUpdate() {//done
        refundItemRepository = new RefundItemRepository();
        inventoryDB = new InventoryRepository();
        refundDB = new RefundRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        sizeDB = new SizeRepository();
        paymentDB = new PaymentRepository();
        saleDB = new SaleRepository();
        userDB = new UserRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Product product = new Product("TestProduct1", 10);
        String productID = productDB.add2(product);
        Size size = new Size("XXL");
        int sizeID = sizeDB.add(size);
        Inventory inven = new Inventory(branchID, sizeID, productID,"TestBarcode", 10);
        int invenID = inventoryDB.add(inven);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        Refund refund = new Refund(saleID, Timestamp.valueOf(LocalDateTime.now()), RefundStatusEnum.NEW);
        int refundID = refundDB.add(refund);
        
        RefundItem refundItem = new RefundItem(invenID, refundID);
        int ID = refundItemRepository.add(refundItem);
        
        refundItem.Id = ID;
        
        Boolean result = refundItemRepository.update(refundItem);
        
        assertEquals( Boolean.class , result.getClass() );
    }

   
    @Test
    public void testGetById_int() {//done
        refundItemRepository = new RefundItemRepository();
        inventoryDB = new InventoryRepository();
        refundDB = new RefundRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        sizeDB = new SizeRepository();
        paymentDB = new PaymentRepository();
        saleDB = new SaleRepository();
        userDB = new UserRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Product product = new Product("TestProduct1", 10);
        String productID = productDB.add2(product);
        Size size = new Size("XXL");
        int sizeID = sizeDB.add(size);
        Inventory inven = new Inventory(branchID, sizeID, productID,"TestBarcode", 10);
        int invenID = inventoryDB.add(inven);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        Refund refund = new Refund(saleID, Timestamp.valueOf(LocalDateTime.now()), RefundStatusEnum.NEW);
        int refundID = refundDB.add(refund);
        
        RefundItem refundItem = new RefundItem(invenID, refundID);
        int ID = refundItemRepository.add(refundItem);
        
        refundItem.Id = ID;
        RefundItem result =  (RefundItem) refundItemRepository.getById(refundItem.Id);
        
        assertEquals(RefundItem.class, result.getClass() );
        
    }
}
