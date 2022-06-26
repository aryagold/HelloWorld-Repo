/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import org.junit.Test;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.POS.Model.Payment;
import za.co.vzap.POS.Model.PaymentTypeEnum;
import za.co.vzap.POS.Model.Refund;
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
public class RefundRepositoryTest { // throws sql exception
    
    private IRepository refundRepository;
    private IRepository saleDB;
    private IRepository paymentDB;
    private IRepository userDB;
    private IRepository branchDB;
    
    @Test
    public void testAdd() {// works
        
        refundRepository = new RefundRepository();
        saleDB = new SaleRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard", true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser1", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestEMail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        Refund refund = new Refund(saleID, Timestamp.valueOf(LocalDateTime.now()), RefundStatusEnum.NEW);
       
        Integer result = refundRepository.add(refund);
        
        assertEquals(Integer.class, result.getClass() );
        
    }

    
    @Test
    public void testUpdate() { // works
        refundRepository = new RefundRepository();
        saleDB = new SaleRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard", true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser1", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestEMail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        Refund refund = new Refund(saleID, Timestamp.valueOf(LocalDateTime.now()), RefundStatusEnum.NEW);
        
        int ID = refundRepository.add(refund);
        
        refund.Id = ID;
        refund.setSaleId("TestSaleId2");
        
        Boolean result = refundRepository.update(refund);
        
        assertEquals( Boolean.class , result.getClass() );
       
    }

    
    @Test
    public void testGetById_int() {//works
        refundRepository = new RefundRepository();
        saleDB = new SaleRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard", true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser1", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestEMail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        String saleID = saleDB.add2(sale);
        
        Refund refund = new Refund(saleID, Timestamp.valueOf(LocalDateTime.now()), RefundStatusEnum.NEW);
           
        int ID = refundRepository.add(refund);
        
        refund.Id = ID;
        
        Refund result = (Refund)refundRepository.getById(refund.Id);
        
        assertEquals(Refund.class, result.getClass());
        
    }
}
