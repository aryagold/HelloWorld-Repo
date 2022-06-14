package za.co.vzap.POS.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Communication.Email.Email;
import za.co.vzap.Communication.Model.CommunicationDto;
import za.co.vzap.Communication.Model.EmailTypeEnum;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.POS.Model.IBT;
import za.co.vzap.POS.Model.IBTStatusEnum;
import za.co.vzap.POS.Model.IbtDto;
import za.co.vzap.POS.Model.Payment;
import za.co.vzap.POS.Model.PaymentTypeEnum;
import za.co.vzap.POS.Model.Refund;
import za.co.vzap.POS.Model.RefundDto;
import za.co.vzap.POS.Model.RefundItem;
import za.co.vzap.POS.Model.RefundItemDto;
import za.co.vzap.POS.Model.RefundStatusEnum;
import za.co.vzap.POS.Model.Sale;
import za.co.vzap.POS.Model.SaleDto;
import za.co.vzap.POS.Model.SaleLineItem;
import za.co.vzap.POS.Model.SaleLineItemDto;
import za.co.vzap.POS.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.IBTRepository;
import za.co.vzap.Sale.Repository.PaymentRepository;
import za.co.vzap.Sale.Repository.RefundItemRepository;
import za.co.vzap.Sale.Repository.RefundRepository;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;
import za.co.vzap.Sale.Repository.SaleRepository;
import za.co.vzap.Interface.Service.IPosService;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

public class PosService implements IPosService {

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

    public PosService(IRepository productRepository, IRepository saleRepository, IRepository refundRepository, IRepository refundItemRepository, IRepository inventoryRepository, IRepository saleLineItemRepository, IRepository paymentRepository, IRepository sizeRepository, IRepository ibtRepository, IRepository branchRepository, IRepository userRepository) {
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
        this.userRepository = new UserRepository();        
    }

    @Override
    public SaleDto addSale(SaleDto dto) {
        Sale sale = PosMapper.toSale(dto);

        sale.setDate(Timestamp.valueOf(LocalDateTime.now()));

        String id = saleRepository.add2(sale);

        for (SaleLineItemDto itemDto : dto.lineitems) {
            itemDto.saleId = id;

            SaleLineItem item = PosMapper.toSaleLineItem(itemDto);

            saleLineItemRepository.add(item);
        }

        dto = getSale(id);

        sendSalesReceipt(dto);

        return dto;
    }

    @Override
    public SaleDto getSale(String id) {
        Sale sale = (Sale) saleRepository.getById(id);

        sale.items = saleLineItemRepository.getWhere("saleId", id);

        Payment payment = (Payment) paymentRepository.getById(sale.getPaymentId());

        SaleDto dto = PosMapper.toSaleDto(sale);

        dto.payment = payment;

        return dto;
    }

    private boolean randomizePayment() {
        int number = (int) (Math.random() + 1) * 5;

        return number != 4;
    }

    @Override
    public int addRefund(Refund refund) {
        refund.setDate(Timestamp.valueOf(LocalDateTime.now()));

        return refundRepository.add(refund);

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

    }

    @Override
    public RefundDto completeRefund(int refundId) {
        Refund refund = (Refund) refundRepository.getById(refundId);
        Sale sale = (Sale) saleRepository.getById(refund.getSaleId());

        CommunicationDto dto = new CommunicationDto();

        refund.setStatus(RefundStatusEnum.COMPLETED);

        refundRepository.update(refund);

        if (refund.getStatus() == RefundStatusEnum.COMPLETED) {

            dto.emailType = EmailTypeEnum.REFUNDRECEIPT;
            dto.emailAddressTo = sale.getCustomerEmail();
            dto.data = toRefundDto(refund);

            new Email(dto).start();
        }

        return (RefundDto) dto.data;
    }

    @Override
    public boolean reserveSale(String saleID) {

        Sale sale = (Sale) saleRepository.getById(saleID);
        sale.setStatus(SaleStatusEnum.RESERVED);
        sale.setPaymentId(0);

        if (sale.getStatus() == SaleStatusEnum.RESERVED) {
            CommunicationDto dto = new CommunicationDto();

            dto.emailType = EmailTypeEnum.RESERVENOTIFICATION;
            dto.emailAddressTo = sale.getCustomerEmail();
            dto.data = PosMapper.toSaleDto(sale);

            new Email(dto).start();
        }

        return saleRepository.update(sale);

    }

    @Override
    public IbtDto addIbt(IbtDto dto) {
        IBT ibt = PosMapper.toIbt(dto);

        ibt.setStatus(IBTStatusEnum.REQUESTED);

        ibtRepository.add(ibt);

        return toIbtDto(ibt);
    }
    
    @Override
    public IbtDto updateIbt(IbtDto dto) {
        IBT ibt = PosMapper.toIbt(dto);
        
        ibtRepository.update(ibt);
        
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
        ibt.setStatus(IBTStatusEnum.RECEIVED);
        ibtRepository.update(ibt);

        return toIbtDto(ibt);

        // message must be sent to customer's phone to notfy them their product has arrived
    }
    
    @Override
    public List<IbtDto> listIbt(String userId, int type) {
        List<IbtDto> dtos = new ArrayList<>();
        
        User user = (User) userRepository.getById(userId);
        Branch branch = (Branch) branchRepository.getById(user.getBranchId());
        
        List<IBT> ibts = new ArrayList<>();
        
        if(type == 0) {
            ibts = ibtRepository.getWhere("branchIdTo", branch.branchId);
            
            for (IBT ibt : ibts) {
                dtos.add(toIbtDto(ibt));
            }
        }
        
        if(type == 1) {
            List<Inventory> inventoryItems = inventoryRepository.getWhere("branchId", branch.branchId);
            
            for (IBT ibt : ibts) {
                for(Inventory item : inventoryItems) {
                    
                }
                
                dtos.add(toIbtDto(ibt));
            }
        }
       
        
        return dtos;
    }


    private RefundDto toRefundDto(Refund refund) {
        RefundDto dto = new RefundDto();
        List<RefundItem> items = refundItemRepository.getWhere("refundId", refund.Id);

        dto.Id = refund.Id;
        dto.saleId = refund.getSaleId();
        dto.date = refund.getDate();

        for (RefundItem item : items) {
            dto.refundItems.add(toRefundItemDto(item));
        }

        return dto;
    }

    private IbtDto toIbtDto(IBT ibt) {
        IbtDto dto = new IbtDto();

        dto.Id = ibt.Id;
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

   

    private void sendSalesReceipt(SaleDto saleDto) {
        CommunicationDto dto = new CommunicationDto();

        dto.emailType = EmailTypeEnum.SALESRECEIPT;
        dto.emailAddressTo = saleDto.customerEmail;
        dto.data = saleDto;

        new Email(dto).start();
    }

    

    

}
