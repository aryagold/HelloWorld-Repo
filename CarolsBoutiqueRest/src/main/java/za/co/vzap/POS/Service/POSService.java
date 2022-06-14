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
import za.co.vzap.Sale.Model.IbtDto;
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
        sale.setPaymentId(0);
        
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
    
    private boolean randomizePayment(){
        int number = (int)(Math.random()+1)*5;
        
        return number != 4;  
    }

    @Override
    public int completeSale(Payment payment, String saleId) {
        Sale sale = (Sale) saleRepository.getById(saleId);
        int paymentId = 0;
        
        
        if(payment.getType() == PaymentTypeEnum.CASH) {
            payment.setCardNumber("Cash Payment");
            payment.setApproved(true);
            
            sale.setStatus(SaleStatusEnum.COMPLETED);
           
            //email receipt 
            
        } else {
        
            if(randomizePayment()) {
                sale.setStatus(SaleStatusEnum.COMPLETED);
                
                payment.setApproved(true);
            
                //email receipt to customer's provided email address on this sale object
            
            } else {
                sale.setStatus(SaleStatusEnum.NEW);
                
                payment.setApproved(false);
            }
        }
        
        paymentId = paymentRepository.add(payment);
        sale.setPaymentId(paymentId);
        
        saleRepository.update(sale);
        
        return paymentId;
          
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
       sale.setPaymentId(0);
       
       return saleRepository.update(sale);
       
       // email thread to count down and send notifications then if time runs out change sale to cancelled.
    }
    
     @Override
     public IbtDto requestIbt(int inventoryIdFrom, String branchIdTo, int quantity, String phoneNumber, String emailAddress) {
         IBT ibt = new IBT();
         
         ibt.setInventoryIdFrom(inventoryIdFrom);
         ibt.setBranchIdTo(branchIdTo);
         ibt.setQuantity(quantity);
         ibt.setPhoneNumber(phoneNumber);
         ibt.setEmailAddress(emailAddress);
         ibt.setStatus(IBTStatusEnum.REQUESTED);
         
         ibtRepository.add(ibt);
         
         return toIbtDto(ibt);

     }
    
     @Override
     public IbtDto approveIbt(int id) {
         IBT ibt = (IBT) ibtRepository.getById(id);
         ibt.setStatus(IBTStatusEnum.APPROVED);
         ibtRepository.update(ibt);     
         
         return toIbtDto(ibt);

     }

     @Override
     public IbtDto declineIbt(int id) {
         IBT ibt = (IBT) ibtRepository.getById(id);
         ibt.setStatus(IBTStatusEnum.DECLINED);
         ibtRepository.update(ibt);

         return toIbtDto(ibt);
     }

     @Override
     public IbtDto ibtReceived(int id) {
         IBT ibt = (IBT) ibtRepository.getById(id);
         ibt.setStatus(IBTStatusEnum.COMPLETED);
         ibtRepository.update(ibt);

         return toIbtDto(ibt);
         
         // message must be sent to customer's phone to notfy them their product has arrived
     }

     @Override
     public void payIBT(InventoryDto dto, int id, Sale sale, Payment payment) {
         LocalDateTime date = LocalDateTime.now();
         int paymentId = 0;
         sale.setDate(Timestamp.valueOf(date));
         sale.setPaymentId(paymentId);
         sale.setStatus(SaleStatusEnum.NEW);
         
         IBT ibt = (IBT) ibtRepository.getById(id);
         
//         String branchId = ibt.getBranchIdFrom();
//         sale.setUserId(branchId);
         
         String saleId = addSale(sale);
         
         completeSale(payment, saleId);
         
         saleLineItemRepository.add(new SaleLineItem(saleId, dto.Id));
         
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
    
    private IbtDto toIbtDto(IBT ibt) {
        IbtDto dto = new IbtDto();
        
        dto.inventoryIdFrom = ibt.getInventoryIdFrom();
        dto.branchIdTo = ibt.getBranchIdTo();
        dto.phoneNumber = ibt.getPhoneNumber();
        dto.emailAddress = ibt.getEmailAddress();
        dto.quantity = ibt.getQuantity();
        dto.statusName = ibt.getStatus().name();
        
        Inventory inventoryFrom = (Inventory) inventoryRepository.getById(ibt.getInventoryIdFrom());
        String branchIdFrom = inventoryFrom.getBranchId();
        Branch branchFrom = (Branch) branchRepository.getById(branchIdFrom);
        dto.branchNameFrom = branchFrom.getName();
        
        Branch branchTo = (Branch) branchRepository.getById(ibt.getBranchIdTo());
        dto.branchNameTo = branchTo.getName();
        
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

    @Override
    public List<IbtDto> viewIbt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
