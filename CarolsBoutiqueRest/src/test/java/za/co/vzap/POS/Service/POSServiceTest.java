/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.POS.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.POS.Model.Payment;
import za.co.vzap.POS.Model.PaymentTypeEnum;
import za.co.vzap.POS.Model.Refund;
import za.co.vzap.POS.Model.RefundItemDto;
import za.co.vzap.POS.Model.Sale;
import za.co.vzap.POS.Model.SaleLineItem;
import za.co.vzap.POS.Model.SaleLineItemDto;
import za.co.vzap.POS.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.PaymentRepository;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;
import za.co.vzap.Interface.Service.IPosService;
import za.co.vzap.POS.Model.SaleDto;

/**
 *
 * @author macpe
 */
public class POSServiceTest {
    
        private IRepository productRepository;
        private IRepository saleRepository;
        private IRepository refundRepository;
        private IRepository refundItemRepository;
        private IRepository inventoryRepository;
        private IRepository saleLineItemRepository;
        private IRepository paymentRepository;
        private IRepository sizeRepository;
        private IRepository ibtRepository;
        private IRepository branchRepository;
        private IRepository userRepository;
        private IPosService posService;

    @Test
    public void testAddSale() {
       
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();
        
        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);
        
        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
        User user = new User( "TestUser" , "TestEmail", branchId , "TestPassword", RoleEnum.TELLER);
        String userID = userRepository.add2(user);
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard", true);
        int paymentID = paymentRepository.add(payment);
        
        Sale sale = new Sale(userID, "TestCustomerEmail" , Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.NEW);
        
        SaleDto dto = posService.addSale(PosMapper.toSaleDto(sale));
        
        assertEquals( SaleDto.class , dto.getClass() );
        
    }

    @Test
    public void testVoidSale() {
      
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
        User user = new User("TestUser", "TestEmail", branchId, "TestPassword", RoleEnum.TELLER);
        String userID = userRepository.add2(user);
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard", true);
        int paymentID = paymentRepository.add(payment);

        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.NEW);
        String saleID = saleRepository.add2(sale);
        
//        Boolean result = posService.voidSale(saleID);
//        
//        assertEquals( Boolean.class , result.getClass() );
        
    }

    @Test
    public void testAddSaleLineItem() throws Exception {
        
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);
       
//        SaleLineItemDto sliDto = new SaleLineItemDto();
//        
//        SaleLineItemDto result = posService.addSaleLineItem(sliDto);
        
    }

    @Test
    public void testCompleteSale() {

        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);
 
        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
        User user = new User("TestUser", "TestEmail", branchId, "TestPassword", RoleEnum.TELLER);
        String userID = userRepository.add2(user);
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard", true);
        int paymentID = paymentRepository.add(payment);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.NEW);
        String saleID = saleRepository.add2(sale);
        
//        Boolean result = posService.completeSale(payment, saleID);
//        
//        assertEquals( Boolean.class, result.getClass());
        
    }

    @Test
    public void testGetSaleLineItems() {
        
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
        User user = new User("TestUser", "TestEmail", branchId, "TestPassword", RoleEnum.TELLER);
        String userID = userRepository.add2(user);
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard", true);
        int paymentID = paymentRepository.add(payment);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.NEW);
        String saleID = saleRepository.add2(sale);

//        List<SaleLineItemDto> result = posService.getSaleLineItems(saleID);
//        
//        assertEquals( List.class , result.getClass());
        
    }

    @Test
    public void testAddRefund() {
        
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
        User user = new User("TestUser", "TestEmail", branchId, "TestPassword", RoleEnum.TELLER);
        String userID = userRepository.add2(user);
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard", true);
        int paymentID = paymentRepository.add(payment);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.NEW);
        String saleID = saleRepository.add2(sale);

//        Refund refund = new Refund(saleID, Timestamp.valueOf(LocalDateTime.now()));
//
//        Integer result = posService.addRefund(refund);
//        
//        assertEquals( Integer.class , result.getClass() );
        
    }

    @Test
    public void testAddRefundItem() {
       
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

       RefundItemDto rid = new RefundItemDto();
       
//       RefundItemDto result = posService.addRefundItem(rid);
//       
//        assertEquals( RefundItemDto.class , result.getClass() );
        
    }

    @Test
    public void testDeleteSaleLineItem() {
        
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
        User user = new User("TestUser", "TestEmail", branchId, "TestPassword", RoleEnum.TELLER);
        String userID = userRepository.add2(user);
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard", true);
        int paymentID = paymentRepository.add(payment);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.NEW);
        String saleID = saleRepository.add2(sale);
        
        Product product = new Product("TestProduct", 50);
        String productID = productRepository.add2(product);
        Size size = new Size("Large");
        int sizeID = sizeRepository.add(size);
        Inventory inven = new Inventory(branchId, sizeID, productID, "Testbracode" , 50 );
        int inventoryID = inventoryRepository.add(inven);
        
        SaleLineItem sli = new SaleLineItem(saleID, inventoryID);
        int sliID = saleLineItemRepository.add(sli);
        
//        Boolean result = posService.deleteSaleLineItem(sliID);
//        
//        assertEquals( Boolean.class , result.getClass() );
        
    }

    @Test
    public void testReserveSale() {
        
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
        User user = new User("TestUser", "TestEmail", branchId, "TestPassword", RoleEnum.TELLER);
        String userID = userRepository.add2(user);
        Payment payment = new Payment(PaymentTypeEnum.CARD, "TestCard", true);
        int paymentID = paymentRepository.add(payment);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.NEW);
        String saleID = saleRepository.add2(sale);
        
        Boolean result = posService.reserveSale(saleID);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

    @Test
    public void testRequestIBT() {
        
        productRepository = new ProductRepository();
        saleRepository = new SaleRepository();
        refundRepository = new RefundRepository();
        refundItemRepository = new RefundItemRepository();
        inventoryRepository = new InventoryRepository();
        saleLineItemRepository = new SaleLineItemRepository();
        paymentRepository = new PaymentRepository();
        sizeRepository = new SizeRepository();
        ibtRepository = new IBTRepository();
        branchRepository = new BranchRepository();
        userRepository = new UserRepository();

        posService = new PosService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        Branch branch = new Branch("Testbranch", 10000, 1000);
        String branchId = branchRepository.add2(branch);
       
        InventoryDto dto = new InventoryDto();
        
        //this method needs a return type 
        // suggestion is an If statement int the request IBT method.
        
    }

    @Test
    public void testAcceptIBT() {
       //needs a return type.
    }

    @Test
    public void testDeclineIBT() {
      //needs a return type
    }

    @Test
    public void testIBTReceived() {
       //return type needed
    }

    @Test
    public void testPayIBT() {
       //needs return type
    }
    
}
