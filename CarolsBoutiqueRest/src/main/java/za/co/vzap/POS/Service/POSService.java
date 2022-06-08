package za.co.vzap.POS.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Sale.Model.IEntity;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleLineItemDto;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.PaymentRepository;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;

 public class POSService implements IPOSService {
    private IRepository productRepository;
    private IRepository saleRepository;
    private IRepository refundRepository;
    private IRepository refundItemRepository;
    private IRepository inventoryRepository;
    private IRepository saleLineItemRepository;
    private IRepository paymentRepository;
    private IRepository sizeRepository;
    
    public POSService(IRepository productRepository, IRepository saleRepository, IRepository refundRepository, IRepository refundItemRepository, IRepository inventoryRepository, IRepository saleLineItemRepository, IRepository paymentRepository, IRepository sizeRepository) {
       this.productRepository = new ProductRepository();
       this.saleRepository = new SaleRepository();
       this.refundRepository = new RefundRepository();
       this.refundItemRepository = new RefundItemRepository();
       this.inventoryRepository = new InventoryRepository();
       this.saleLineItemRepository = new SaleLineItemRepository();
       this.paymentRepository = new PaymentRepository();
       this.sizeRepository = new SizeRepository();
    }
    
    @Override
    public String addSale(Sale sale) {
        sale.setDate(Timestamp.valueOf(LocalDateTime.now()));
        
        String id = saleRepository.add2(sale);
        
        return id;
    }
    
    public boolean voidSale(Sale sale) {
        sale.setStatus(SaleStatusEnum.CANCELLED);
        
        boolean voided = saleRepository.update(sale);
        
        return voided;
    }
    
    @Override 
    public SaleLineItemDto addSaleLineItem(SaleLineItemDto dto) throws Exception {
       
       List<Inventory> items = inventoryRepository.getWhere("barcode", dto.barcode);
       
       if(items.size() == 0) {
           throw new Exception("Invalid barcode");
       }
       
       Inventory inventory = items.get(0);
       
       SaleLineItem sli = new SaleLineItem(dto.saleId, inventory.Id);
       
       int id = saleLineItemRepository.add(sli);
       
       dto.Id = id;
       dto.inventoryId = inventory.Id;
       
       Product product = (Product) productRepository.getById(inventory.getProductId());
       dto.price = product.getPrice();
       dto.productName = product.getName();
       
       List<Size> sizes = sizeRepository.getWhere("id", inventory.getSizeId());
       Size size = sizes.get(0);
       dto.sizeName = size.getSize();
       
       return dto;
       
       // build up receipt with sale line items
    }
    
    private int addPayment(Payment payment) {
        if(randomizePayment()) {
            
            payment.setApproved(true);
            
        } else {
        
            payment.setApproved(false);
        }
        
        return paymentRepository.add(payment);
        
        
    }
    
    private boolean randomizePayment(){
        int number = (int)(Math.random()+1)*5;
        
        return number != 4;  
    }

    @Override
    public boolean confirmSale(Sale sale) {
        Payment payment = (Payment) paymentRepository.getById(sale.getPaymentId());
        
        if(randomizePayment()) {
            
            sale.setStatus(SaleStatusEnum.COMPLETED);
            payment.setApproved(true);
            
            //email receipt to customer's provided email address on this sale object
        } else {
            
            sale.setStatus(SaleStatusEnum.CANCELLED);
            payment.setApproved(false);
        }
        
        int paymentId = addPayment(payment);
        
        sale.setPaymentId(paymentId);
        
        return saleRepository.update(sale);
        
        
    }
    
    public List<SaleLineItemDto> getSaleLineItems(String saleId) {
        List<SaleLineItemDto> dtos = new ArrayList<>();
        
        List<SaleLineItem> items = saleLineItemRepository.getWhere("saleId", saleId);
        
        for(SaleLineItem item : items) {
            SaleLineItemDto dto = new SaleLineItemDto();
            
            dto.Id = item.Id;
            dto.saleId = saleId;
            dto.inventoryId = item.getInventoryId();
            
            Inventory inventory = (Inventory) inventoryRepository.getById(item.getInventoryId());
            
            Product product = (Product) productRepository.getById(inventory.getProductId());
            
            dto.productName = product.getName();
            dto.price = product.getPrice();
            
            Size size = (Size) sizeRepository.getById(inventory.getSizeId());
            
            dto.sizeName = size.getSize();
            
            dtos.add(dto);
        }
        
        return dtos;
    }
    
    
     @Override
    public int addRefund(Refund refund) {
        
        String saleId = refund.getSaleId();
        
        Sale sale = (Sale) saleRepository.getById(saleId);
        
        sale.setStatus(SaleStatusEnum.CANCELLED);
        
        saleRepository.update(sale);
        
        List<SaleLineItem> items = (List<SaleLineItem>) saleLineItemRepository.getWhere("saleId", saleId);
        
        for(SaleLineItem item : items) {
            saleLineItemRepository.deleteById(item.Id);
            
            Inventory inventory = (Inventory) inventoryRepository.getById(item.getInventoryId());
            
            inventory.setQuantity(inventory.getQuantity() + 1);   
        }
        
        return refundRepository.add(refund);
        
        //email customer with refund receipt
       
    }
    
    @Override
    public int addRefundItem(RefundItem refundItem, int refundId) {
        Refund refund = (Refund) refundRepository.getById(refundId);
        
        String saleId =refund.getSaleId();
        
        int inventoryId = refundItem.getInventoryId();
        
        Inventory inventory = (Inventory) inventoryRepository.getById(inventoryId);
        
        inventory.setQuantity(inventory.getQuantity() + 1);
        
        inventoryRepository.update(inventory);
        
        List<SaleLineItem> saleLineItems = (List<SaleLineItem>) saleLineItemRepository.getWhere("saleId", saleId);
        
        return refundItemRepository.add(refundItem);
        
        //build up refund receipt with this item
    }

    @Override
    public boolean deleteSaleLineItem(int saleLineItemId) {
        SaleLineItem saleLineItem = (SaleLineItem) saleLineItemRepository.getById(saleLineItemId);
        
        int inventoryId = saleLineItem.getInventoryId();
        
        Inventory inventory = (Inventory) inventoryRepository.getById(inventoryId);
        
        inventory.setQuantity(inventory.getQuantity() + 1);
        
        return saleLineItemRepository.deleteById(saleLineItemId);
    }

    @Override
    public boolean reserveSale(String saleID) {
        
       Sale sale = (Sale) saleRepository.getById(saleID);
       sale.setStatus(SaleStatusEnum.RESERVED);
       
       return saleRepository.update(sale);
       
       // email thread to count down and send notifications then if time runs out change sale to cancelled.
    }
    

    @Override
    public void email(IEntity arg0) {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }        

    
    
}
