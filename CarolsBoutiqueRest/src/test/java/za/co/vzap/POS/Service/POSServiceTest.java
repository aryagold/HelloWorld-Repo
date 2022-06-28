/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.POS.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import za.co.vzap.POS.Model.SaleDto;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.POS.Model.IBT;
import za.co.vzap.POS.Model.IBTStatusEnum;
import za.co.vzap.POS.Model.IbtDto;
import za.co.vzap.POS.Model.RefundDto;
import za.co.vzap.POS.Model.RefundStatusEnum;

/**
 *
 * @author macpe
 */
public class POSServiceTest {// complete
    
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
        private IPOSService posService;

    @Test
    public void testAddSale() {//done
       
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
        
        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);
        
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
    public void testGetSale(){//done
        
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

        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        SaleDto result = posService.getSale("SL001");
        
        assertEquals( SaleDto.class , result.getClass() );
        
    }
    
    @Test
    public void testGetRefund(){//done
        
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

        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        RefundDto result = posService.getRefund(1);
        
        assertEquals( RefundDto.class , result.getClass() );
        
    }
    
    @Test
    public void testAddIbt(){//done
        
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

        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        IBT ibt = new IBT(61, "BR003" , 10 , "TestPhoneNumber","TestEmail", IBTStatusEnum.REQUESTED);
        IbtDto ibtDto = PosMapper.toIbtDto(ibt);
        
        IbtDto result = posService.addIbt(ibtDto);
        
        assertEquals( IbtDto.class , result.getClass() );
        
    }
    
    @Test
    public void testUpdateIbt(){ //done
        
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

        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        IBT ibt = new IBT(61, "BR003", 10, "TestPhoneNumber", "TestEmail", IBTStatusEnum.REQUESTED);
        
        IbtDto ibtDto = posService.addIbt(PosMapper.toIbtDto(ibt));
        ibtDto.status = IBTStatusEnum.REQUESTED;
        
        IbtDto result = posService.updateIbt(ibtDto);
        
        assertEquals( IbtDto.class , result.getClass() );
        
    }
    
    @Test
    public void testListIbt(){//done
        
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

        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);
  
        List<IbtDto> result = posService.listIbt( "US001" , 1 );
        
        assertEquals( ArrayList.class , result.getClass());
        
    }
    
    @Test
    public void testGetReserved(){//done
        
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

        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        List<SaleDto> result = posService.getReserved();
        
        assertEquals( ArrayList.class , result.getClass() );
        
    }

    @Test
    public void testAddRefund() {//done
        
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

        posService = new POSService(productRepository, saleRepository, refundRepository, refundItemRepository, inventoryRepository, saleLineItemRepository, paymentRepository, sizeRepository, ibtRepository, branchRepository, userRepository);

        Refund refund = new Refund( "SL006" , Timestamp.valueOf(LocalDateTime.now()) , RefundStatusEnum.NEW);

        RefundDto result = posService.addRefund(PosMapper.toRefundDto(refund));
        
        assertEquals( RefundDto.class , result.getClass() );
        
    }
}
