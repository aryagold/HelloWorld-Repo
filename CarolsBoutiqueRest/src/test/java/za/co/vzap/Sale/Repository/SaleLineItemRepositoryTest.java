/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.nio.file.attribute.UserDefinedFileAttributeView;
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
import za.co.vzap.POS.Model.Sale;
import za.co.vzap.POS.Model.SaleLineItem;
import za.co.vzap.POS.Model.SaleStatusEnum;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

/**
 *
 * @author macpe
 */
public class SaleLineItemRepositoryTest {
    
    private IRepository saleLineItemRepository;
    private IRepository branchDB;
    private IRepository productDB;
    private IRepository sizeDB;
    private IRepository invenDB;
    private IRepository paymentDB;
    private IRepository saleDB;
    private IRepository userDB;
    
    @Test
    public void testAdd() {
        saleLineItemRepository = new SaleLineItemRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        sizeDB = new SizeRepository();
        invenDB = new InventoryRepository();
        saleDB = new SaleRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Product product = new Product("TestProduct1", 10);
        String productID = productDB.add2(product);
        Size size = new Size("XXL");
        int sizeID = sizeDB.add(size);
        Inventory inven = new Inventory(branchID, sizeID, productID,"TestBarcode", 10);
        int invenID = invenDB.add(inven);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        SaleLineItem saleLineItem = new SaleLineItem(saleID, invenID);
        
        Integer result = saleLineItemRepository.add(saleLineItem);
       
        assertEquals( Integer.class , result.getClass());
        
    }

    
    @Test
    public void testUpdate() {
        
        saleLineItemRepository = new SaleLineItemRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        sizeDB = new SizeRepository();
        invenDB = new InventoryRepository();
        saleDB = new SaleRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Product product = new Product("TestProduct1", 10);
        String productID = productDB.add2(product);
        Size size = new Size("XXL");
        int sizeID = sizeDB.add(size);
        Inventory inven = new Inventory(branchID, sizeID, productID,"TestBarcode", 10);
        int invenID = invenDB.add(inven);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        SaleLineItem saleLineItem = new SaleLineItem(saleID, invenID);
        
        
        int ID = saleLineItemRepository.add(saleLineItem);
        
        saleLineItem.Id = ID;
        saleLineItem.setInventoryId(1234);
        
        Boolean result = saleLineItemRepository.update(saleLineItem);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

    
    @Test
    public void testGetById_int() {
        
        saleLineItemRepository = new SaleLineItemRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        sizeDB = new SizeRepository();
        invenDB = new InventoryRepository();
        saleDB = new SaleRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Product product = new Product("TestProduct1", 10);
        String productID = productDB.add2(product);
        Size size = new Size("XXL");
        int sizeID = sizeDB.add(size);
        Inventory inven = new Inventory(branchID, sizeID, productID,"TestBarcode", 10);
        int invenID = invenDB.add(inven);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        SaleLineItem saleLineItem = new SaleLineItem(saleID, invenID);
        
        
        int ID = saleLineItemRepository.add(saleLineItem);
        
        saleLineItem.Id = ID;
        
        SaleLineItem result  = (SaleLineItem) saleLineItemRepository.getById(saleLineItem.Id);
      
        assertEquals(SaleLineItem.class, result.getClass() );
       
    }
}