/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Sale.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IBTStatusEnum;

/**
 *
 * @author macpe
 */
public class IBTRepositoryTest {// null pointer on result in teh get by iD method.
    
    private IRepository ibtRepository;
    private IRepository branchDB;
    private IRepository productDB;
    
    @Test
    public void testAdd() {
        ibtRepository = new IBTRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchIDTo = branchDB.add2(branch);
        Branch branch2 = new Branch("TestBranch2", 5000, 500);
        String branchIDFrom = branchDB.add2(branch2);
        Product product = new Product("TestProduct", 10);
        String productID = productDB.add2(product);
        
        IBT ibt = new IBT(branchIDFrom, branchIDTo, productID, 2, "072Testing", IBTStatusEnum.REQUESTED);
        
        Integer result = ibtRepository.add(ibt);
       
        assertEquals(Integer.class, result.getClass() );
        
    }

   
    @Test
    public void testUpdate() {
        ibtRepository = new IBTRepository();
        ibtRepository = new IBTRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchIDTo = branchDB.add2(branch);
        Branch branch2 = new Branch("TestBranch2", 5000, 500);
        String branchIDFrom = branchDB.add2(branch2);
        Product product = new Product("TestProduct", 10);
        String productID = productDB.add2(product);

        
        IBT ibt = new IBT(branchIDFrom, branchIDTo, productID, 2, "072Testing", IBTStatusEnum.REQUESTED);
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
        ibt.setQuantity(20);
        
        Boolean result = ibtRepository.update(ibt);
        
        assertEquals( Boolean.class , result.getClass() );
       
    }

    
    @Test
    public void testGetById_int() {
        ibtRepository = new IBTRepository();
        ibtRepository = new IBTRepository();
        branchDB = new BranchRepository();
        productDB = new ProductRepository();
        
        Branch branch = new Branch("TestBranch1", 10000, 1000);
        String branchIDTo = branchDB.add2(branch);
        Branch branch2 = new Branch("TestBranch2", 5000, 500);
        String branchIDFrom = branchDB.add2(branch2);
        Product product = new Product("TestProduct", 10);
        String productID = productDB.add2(product);

        
        IBT ibt = new IBT(branchIDFrom, branchIDTo, productID, 2, "072Testing", IBTStatusEnum.REQUESTED);
        int ID = ibtRepository.add(ibt);
        
        ibt.Id = ID;
        
        IBT result = (IBT) ibtRepository.getById(ibt.Id);
        System.out.println(result);
       
        assertEquals(IBT.class, result.getClass());
       
    }
}
