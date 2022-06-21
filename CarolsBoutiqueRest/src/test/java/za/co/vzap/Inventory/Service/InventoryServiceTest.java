/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Repository.CategoryRepository;
import za.co.vzap.Inventory.Repository.InventoryControlRepository;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductCategoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Sale.Repository.SaleRepository;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;


/**
 *
 * @author macpe
 */
public class InventoryServiceTest {
    
    private IInventoryService inventoryService;
    private IRepository productDB;
    private IRepository productCategoryDB;
    private IRepository inventoryControlDB;
    private IRepository inventoryDB;
    private IRepository sizeDB;
    private IRepository saleDB;
    private IRepository categoryDB;
    private IRepository branchDB;
    private IRepository userDB;
    
    @Test
    public void testAddProduct() {
        
        productDB = new ProductRepository();
        productCategoryDB = new ProductCategoryRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        saleDB = new SaleRepository();
        categoryDB = new CategoryRepository();
        branchDB = new BranchRepository();
//        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, categoryDB, branchDB);

        Product product = new Product("TestProduct", 100);
        product.productId = productDB.add2(product);
        Category category = new Category("TestCategory");
        category.categoryId = categoryDB.add2(category);
        
        List<String> categoryIDs = new ArrayList<>();
        categoryIDs.add(category.categoryId);
        
//        String result = productService.addProduct(product, categoryIDs);
//        
//        assertEquals(String.class, result.getClass());
        
    }
    
    @Test
    public void testAddCategory() {
        
        productDB = new ProductRepository();
        productCategoryDB = new ProductCategoryRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        saleDB = new SaleRepository();
        categoryDB = new CategoryRepository();
        branchDB = new BranchRepository();
//        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, categoryDB, branchDB);

        Category category = new Category("TestCategory");
        
//        String result = inventoryService.addCategory(category);
//        
//        assertEquals( String.class , result.getClass());
        
    }
   
    @Test
    public void testAddInventoryControl() throws Exception {
       
        productDB = new ProductRepository();
        productCategoryDB = new ProductCategoryRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        saleDB = new SaleRepository();
        categoryDB = new CategoryRepository();
        branchDB = new BranchRepository();
//        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, categoryDB, branchDB);

        userDB = new UserRepository();
        Branch branch = new Branch("TestBranch", 10000, 1000);
        String branchID = branchDB.add2(branch);
        User user = new User("TestUser", "Test@Email" , branchID, "TestPassword", RoleEnum.TELLER);
        user.userId = userDB.add2(user);
        
//        InventoryDto result = inventoryService.addInventoryControl(user.userId, "TestBarcode", 100);
//        
//        assertEquals(InventoryDto.class,result.getClass());
        
    }
    
    @Test
    public void testAddInventory() {
        
        productDB = new ProductRepository();
        productCategoryDB = new ProductCategoryRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        saleDB = new SaleRepository();
        categoryDB = new CategoryRepository();
        branchDB = new BranchRepository();
//        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, categoryDB, branchDB);
       
        InventoryDto dto = new InventoryDto();
        
//        String result = inventoryService.addInventory(dto);
//        
//        assertEquals( String.class , result.getClass());
        
    }
    
    @Test
    public void testFindProductWithProductId() throws Exception {
        
        productDB = new ProductRepository();
        productCategoryDB = new ProductCategoryRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        saleDB = new SaleRepository();
        categoryDB = new CategoryRepository();
        branchDB = new BranchRepository();
//        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, categoryDB, branchDB);
         
        Product product = new Product("TestProduct1", 20 );
        product.productId = productDB.add2(product);
        
//        List<InventoryDto> result = inventoryService.findProductWithProductId(product.productId);
//        
//        assertEquals( List.class , result.getClass() );
        
    }

    @Test
    public void testFindProductWithBarcode() throws Exception {
        
        productDB = new ProductRepository();
        productCategoryDB = new ProductCategoryRepository();
        inventoryControlDB = new InventoryControlRepository();
        inventoryDB = new InventoryRepository();
        sizeDB = new SizeRepository();
        saleDB = new SaleRepository();
        categoryDB = new CategoryRepository();
        branchDB = new BranchRepository();
//        inventoryService = new InventoryService(productDB, inventoryControlDB, inventoryDB, sizeDB, categoryDB, branchDB);
       
        String barcode = "TestBarcode";
        
//        List<InventoryDto> result = inventoryService.findProductWithBarcode(barcode);
//
//        assertEquals( List.class , result.getClass() );
        
    }
    
}
