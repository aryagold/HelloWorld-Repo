package za.co.vzap.POS.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.ProductCode;
import za.co.vzap.Inventory.Repository.ProductCodeRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.PaymentRepository;
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
    private IRepository inventoryRepository;
    private IRepository productCodeRepository;
    private IRepository saleLineItemRepository;
    private IRepository paymentRepository;
    
    public POSService(){
       productDB = new ProductRepository();
       productDB = new SaleLineItemRepository();
       saleDB = new SaleRepository();
       refundDB = new RefundRepository();//refund record.
       refundItemDB = new RefundItemRepository();// refund the item.
       productCodeRepository = new ProductCodeRepository();
       saleLineItemRepository = new SaleLineItemRepository();
       paymentRepository = new PaymentRepository();
    }
    
    @Override 
    public int addToSale(String saleId, String barcode) {
       List<Inventory> items = inventoryRepository.getWhere("barcode", barcode);
       
       int inventoryId = 0;
        
       for(Inventory item : items) {
           inventoryId = item.Id;
       }
       
       SaleLineItem sli = new SaleLineItem(saleId, inventoryId);//sale line item created.
        
       return saleLineItemDB.add(sli) ;//adding the sale and sale line item to the data base (commit and roleback to be used here later).
    }
    
    private int addPayment(Payment payment) {
        if(randomizePayment()) {
            
            payment.setApproved(true);
            
            // confirmSale();
            
        } else {
        
            payment.setApproved(false);
        
            // cancelSale();
        }
        
        return paymentRepository.add(payment);
        
        
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
    public int addRefund(String barcode) {
        
        List<Inventory> items = inventoryRepository.getWhere("barcode", barcode); 
        
        int inventId = 0;
        
        int productCode = 0;
        
        for(Inventory item : items) {
            item.setQuantity(item.getQuantity() + 1);
            
            inventId = item.Id;
            
            productCode = item.getProductCode();
            
        } 
        
        ProductCode code = (ProductCode) productCodeRepository.getById(productCode);
        
        String productId = code.getProductId();
        
        refundItemDB.add(new RefundItem(productId, 1));
        
        
        List<SaleLineItem> saleLineItems = saleLineItemRepository.getWhere("inventoryId", inventId);
        
        String saleId = "";
        
        for(SaleLineItem sli : saleLineItems) {
            saleId = sli.getSaleId();
            // delete the sale line item? 
        }
        
        email(null);//edit this to send an email. It should take as a parameter an array list that can be unpacked differently in each method. this methods array list will have the price, saleId and product ID.
        return  refundDB.add(new Refund(saleId ,Timestamp.valueOf(LocalDateTime.now())));// creating the record of the refund to add to the database and returning an int.
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
    public int addReserved(String arg0) {
        // this method should add to sale with the extra functionality of emailing the customer at confirmation and after 36 hours 
        // method should also change "reserved" enum to cancelled after 2 days or completed depending of customer ever came.
        return 0;
    }

    @Override
    public void email(IEntity arg0) {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
        
    
}
