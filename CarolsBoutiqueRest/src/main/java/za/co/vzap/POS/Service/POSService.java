package za.co.vzap.POS.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.ProductSaleRepository;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.ProductSale;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;

 public class POSService implements IPOSService {
    private IRepository productDB;
    private IRepository saleLineItemDB;
    private IRepository saleDB;
    private IRepository productSaleDB;
    private IRepository refundDB;
    private IRepository refundItemDB;
    
    public POSService(){
       productDB = new ProductRepository();
       productDB = new SaleLineItemRepository();
       saleDB = new SaleRepository();
       productSaleDB = new ProductSaleRepository();
       refundDB = new RefundRepository();//refund record.
       refundItemDB = new RefundItemRepository();// refund the item.
    }
    
    @Override 
    public int addToSale(String productId, int quantity, String userId, String email, int paymentId, String branchId) {
       
       SaleLineItem sli = new SaleLineItem(productId , quantity);//sale line item created.
      
       Sale sale = new Sale(userId, email, Timestamp.valueOf(LocalDateTime.now()), paymentId , branchId, SaleStatusEnum.RESERVED);// sale created
       
       Product prod = (Product) productDB.getById(productId);// product created.
       
       productSaleDB.add(new ProductSale(productId,sale.saleId));// adding to intersect table(ProductSale).
        
       return saleLineItemDB.add(sli) ;//adding the sale and sale line item to the data base (commit and roleback to be used here later).
    }
    
    private boolean randomizePayment(){
        int number = (int)(Math.random()+1)*5;
        
        return  (((Math.random()+1)*5) != 4);  
    }

    @Override
    public String confirmSale(Sale sale) {
        sale.setStatus(SaleStatusEnum.COMPLETED);
        
        return saleDB.add2(sale);
        
        //email receipt to customer's provided email address on this sale object
    }
    
    @Override
    public void cancelSale(Sale sale) {
        sale.setStatus(SaleStatusEnum.CANCELLED);
    }
    
     @Override
    public int addRefund(String barcode, int quantity) {
        
        Product product = (Product) productDB.getWhere(barcode, 0).get(1);//getting the product.
        
        ProductSale ps = (ProductSale) productSaleDB.getWhere("productID", product.productId).get(1);//getting the Product Sale.
        
        refundItemDB.add(new RefundItem(product.productId, quantity));//creating a refund item which will have the quantity.
        double price = product.getPrice()* quantity ;
        
        String saleID = ps.getSaleId();//getting the sale ID b4 deleting the product sale record.
        
        productSaleDB.deleteById(barcode);// deleting from the product sale database table.
        
        email(null);//edit this to send an email. It should take as a parameter an array list that can be unpacked differently in each method. this methods array list will have the price, saleId and product ID.
        return  refundDB.add(new Refund(saleID,Timestamp.valueOf(LocalDateTime.now())));// creating the record of the refund to add to the database and returning a boolean .
    }

    @Override
    public boolean deleteSaleLineItem(Product product) {
        
       return saleLineItemDB.deleteById(product.productId);// deletes the SaleLineItem related to the product.
    }

    @Override
    public boolean updateToReserved(String saleID) {
        
       Sale sale = (Sale) saleDB.getById(saleID);
       sale.setStatus(SaleStatusEnum.RESERVED);
       
       return saleDB.update(sale);
    }

    @Override
    public void email(IEntity arg0) {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
    
}
