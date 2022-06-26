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
import za.co.vzap.POS.Model.Payment;
import za.co.vzap.POS.Model.PaymentTypeEnum;
import za.co.vzap.POS.Model.Sale;
import za.co.vzap.POS.Model.SaleStatusEnum;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

/**
 *
 * @author macpe
 */
public class SaleRepositoryTest {// complete
    
    private IRepository saleRepository;
    private IRepository paymentDB;
    private IRepository userDB;
    private IRepository branchDB;
    
    @Test
    public void test2Add() {// works
        saleRepository = new SaleRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        
        String result = saleRepository.add2(sale);
        
        assertEquals( String.class , result.getClass() );
        
    }

    @Test

    public void testUpdate() {// works
        
        saleRepository = new SaleRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        
        
        String ID = saleRepository.add2(sale);
        
        sale.saleId = ID ;
        sale.setCustomerEmail("Test2@gmail.com");
        
        Boolean result = saleRepository.update(sale);
        
        assertEquals( Boolean.class , result.getClass() );
        
    }

    @Test
    public void testGetById_String() {
        
        saleRepository = new SaleRepository();
        paymentDB = new PaymentRepository();
        userDB = new UserRepository();
        branchDB = new BranchRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchID = branchDB.add2(branch);
        Payment payment = new Payment(PaymentTypeEnum.CARD,"TestCard" , true);
        int paymentID = paymentDB.add(payment);
        User user = new User("TestUser", "TestEmail", branchID, "TestPassword", RoleEnum.TELLER);
        String userID = userDB.add2(user);
        Sale sale = new Sale(userID, "TestCustomerEmail", Timestamp.valueOf(LocalDateTime.now()), paymentID, SaleStatusEnum.COMPLETED);
        
        
        String ID = saleRepository.add2(sale);
        
        sale.saleId = ID;
        
        Sale result =(Sale) saleRepository.getById(sale.saleId);
        System.out.println(result);
        
        assertEquals( Sale.class, result.getClass());
    }
}
