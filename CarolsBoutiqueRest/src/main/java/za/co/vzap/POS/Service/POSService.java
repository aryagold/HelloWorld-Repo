package za.co.vzap.POS.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IBTStatusEnum;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.PaymentTypeEnum;
import za.co.vzap.Sale.Model.Refund;
import za.co.vzap.Sale.Model.RefundItem;
import za.co.vzap.Sale.Model.RefundItemDto;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleLineItem;
import za.co.vzap.Sale.Model.SaleLineItemDto;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.IBTRepository;
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
    private IRepository ibtRepository;
     private IRepository branchRepository;
    
    public POSService(IRepository productRepository, IRepository saleRepository, IRepository refundRepository, IRepository refundItemRepository, IRepository inventoryRepository, IRepository saleLineItemRepository, IRepository paymentRepository, IRepository sizeRepository, IRepository ibtRepository, IRepository branchRepository) {
       this.productRepository = new ProductRepository();
       this.saleRepository = new SaleRepository();
       this.refundRepository = new RefundRepository();
       this.refundItemRepository = new RefundItemRepository();
       this.inventoryRepository = new InventoryRepository();
       this.saleLineItemRepository = new SaleLineItemRepository();
       this.paymentRepository = new PaymentRepository();
       this.sizeRepository = new SizeRepository();
       this.ibtRepository = new IBTRepository();
       this.branchRepository = new BranchRepository();
    }
    
    @Override
    public String addSale(Sale sale) {
        sale.setDate(Timestamp.valueOf(LocalDateTime.now()));
        
        String id = saleRepository.add2(sale);
        
        return id;
    }
    
    public boolean voidSale(String saleId) {
        Sale sale = (Sale) saleRepository.getById(saleId);
        
        sale.setStatus(SaleStatusEnum.CANCELLED);
        
        return saleRepository.update(sale);
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
       
       return toSaleLineItemDto(sli);
       
       // build up receipt with sale line items
    }
    
    private int addPayment(Payment payment) {
        if(payment.getType() == PaymentTypeEnum.CASH) return paymentRepository.add(payment);
            
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
    public boolean completeSale(Payment payment, String saleId) {
        Sale sale = (Sale) saleRepository.getById(saleId);
        
        if(payment.getType() == PaymentTypeEnum.CASH) {
            payment.setApproved(true);
            addPayment(payment);
        }
        
        if(randomizePayment()) {
            
            sale.setStatus(SaleStatusEnum.COMPLETED);
            payment.setApproved(true);
            
            //email receipt to customer's provided email address on this sale object
            
        } else {
            
            sale.setStatus(SaleStatusEnum.NEW);
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
            SaleLineItemDto dto = toSaleLineItemDto(item);
            
            dtos.add(dto);
        }
        
        return dtos;
    }
    
    
     @Override
    public int addRefund(Refund refund) {   
        refund.setDate(Timestamp.valueOf(LocalDateTime.now()));
        
        return refundRepository.add(refund);
        
        //email customer with refund receipt
       
    }
    
    @Override
    public RefundItemDto addRefundItem(RefundItemDto dto) {
        List<Inventory> items = inventoryRepository.getWhere("barcode", dto.barcode);
        
        Inventory inventory = items.get(0);
        
        inventory.setQuantity(inventory.getQuantity() + 1);
        
        inventoryRepository.update(inventory);
        
        RefundItem refundItem = new RefundItem(inventory.Id, dto.refundId);
        
        refundItemRepository.add(refundItem);
        
        return toRefundItemDto(refundItem);
        
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
     public void requestIBT(InventoryDto dto, String phoneNumber, String branchIdTo) {
         IBT ibt = new IBT();
         ibt.setBranchIdFrom(dto.branchId);
         ibt.setProductId(dto.productId);
         ibt.setQuantity(dto.quantity);
         ibt.setPhoneNumber(phoneNumber);
         ibt.setStatus(IBTStatusEnum.REQUESTED);
         ibt.setBranchIdTo(branchIdTo);
         
         List<Inventory> inventories = inventoryRepository.getWhere("barcode", dto.barcode);
         
         Inventory inventory = inventories.get(0);
         
         inventory.setQuantity(inventory.getQuantity() - ibt.getQuantity());
         
         inventoryRepository.update(inventory);
         
         // possible change in ibt from productId to inventoryId, in order to control stock quantities and sales etc.
         // above 4 lines of code not in correct place. Only after ibt accepted, but works.
         
         int id = ibtRepository.add(ibt);

     }
    
     @Override
     public void acceptIBT(int id) {
         IBT ibt = (IBT) ibtRepository.getById(id);
         ibt.setStatus(IBTStatusEnum.APPROVED);
         ibtRepository.update(ibt);

     }

     @Override
     public void declineIBT(int id) {
         IBT ibt = (IBT) ibtRepository.getById(id);
         ibt.setStatus(IBTStatusEnum.REJECTED);
         ibtRepository.update(ibt);

     }

     @Override
     public void IBTReceived(int id) {
         IBT ibt = (IBT) ibtRepository.getById(id);
         ibt.setStatus(IBTStatusEnum.COMPLETED);
         ibtRepository.update(ibt);

         // message must be sent to customer's phone to notfy them their product has arrived
     }

     @Override
     public void payIBT(List<InventoryDto> dtos, int id, String email, String cardNumber) {
         Payment payment = new Payment();
         int paymentId = 0;
         
         IBT ibt = (IBT) ibtRepository.getById(id);
         
         String branchId = ibt.getBranchIdFrom();
         
         Branch branch = (Branch) branchRepository.getById(branchId);
         
         if(cardNumber == null) {
             payment.setType(PaymentTypeEnum.CASH);
             payment.setApproved(true);
             paymentId = addPayment(payment);
         } else {
             payment.setType(PaymentTypeEnum.CARD);
             paymentId = addPayment(payment);
         }
         
         LocalDateTime date = LocalDateTime.now();
         
         if(payment.isApproved()) {
            String saleId = saleRepository.add2(new Sale(branch.branchId, email, Timestamp.valueOf(date), paymentId, SaleStatusEnum.COMPLETED));
         
            for(InventoryDto item : dtos) {
                saleLineItemRepository.add(new SaleLineItem(saleId, item.Id));
            }
         }

     }
          

    private SaleLineItemDto toSaleLineItemDto(SaleLineItem saleLineItem) {
        SaleLineItemDto dto = new SaleLineItemDto();
        
        dto.Id = saleLineItem.Id;
        dto.inventoryId = saleLineItem.getInventoryId();
        dto.saleId = saleLineItem.getSaleId();
        
        Inventory inventory = (Inventory) inventoryRepository.getById(saleLineItem.getInventoryId());
        dto.barcode = inventory.getBarcode();
        
        Product product = (Product) productRepository.getById(inventory.getProductId());
        dto.productName = product.getName();
        dto.price = product.getPrice();
        
        Size size = (Size) sizeRepository.getById(inventory.getSizeId());
        dto.sizeName = size.getSize();
        
        return dto;
    }
    
    private RefundItemDto toRefundItemDto(RefundItem refundItem) {
        RefundItemDto dto = new RefundItemDto();
        
        dto.Id = refundItem.Id;
        dto.inventoryId = refundItem.getInventoryId();
        dto.refundId = refundItem.getRefundId();
        
        Inventory inventory = (Inventory) inventoryRepository.getById(refundItem.getInventoryId());
        dto.barcode = inventory.getBarcode();
        
        Product product = (Product) productRepository.getById(inventory.getProductId());
        dto.productName = product.getName();
        dto.price = product.getPrice();
        
        Size size = (Size) sizeRepository.getById(inventory.getSizeId());
        dto.sizeName = size.getSize();
        
        return dto;
    }
    
}
