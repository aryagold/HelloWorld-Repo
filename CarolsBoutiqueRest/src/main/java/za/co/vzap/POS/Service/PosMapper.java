package za.co.vzap.POS.Service;

import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.Size;
import za.co.vzap.Inventory.Repository.InventoryRepository;
import za.co.vzap.Inventory.Repository.ProductRepository;
import za.co.vzap.Inventory.Repository.SizeRepository;
import za.co.vzap.POS.Model.IBT;
import za.co.vzap.POS.Model.IbtDto;
import za.co.vzap.POS.Model.Sale;
import za.co.vzap.POS.Model.SaleDto;
import za.co.vzap.POS.Model.SaleLineItem;
import za.co.vzap.POS.Model.SaleLineItemDto;
import za.co.vzap.POS.Model.SaleStatusEnum;
import za.co.vzap.Sale.Repository.SaleLineItemRepository;

public class PosMapper {
    private static IRepository saleLineItemRepository = new SaleLineItemRepository();
    private static IRepository inventoryRepository = new InventoryRepository();
    private static IRepository productRepository = new ProductRepository();
    private static IRepository sizeRepository = new SizeRepository();
    
    // Sale
    
    public static SaleDto toSaleDto(Sale sale) {
        SaleDto dto = new SaleDto();

        dto.saleId = sale.saleId;
        dto.customerEmail = sale.getCustomerEmail();
        dto.date = sale.getDate();
        dto.paymentId = sale.getPaymentId();
        dto.status = sale.getStatus();
        dto.userId = sale.getUserId();
        
        for(SaleLineItem item : sale.items) {
            dto.lineitems.add(toSaleLineItemDto(item));
        }

        return dto;
    }
    
    private static SaleLineItemDto toSaleLineItemDto(SaleLineItem saleLineItem) {
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
    
    public static Sale toSale(SaleDto dto) {
        Sale sale = new Sale();
        
        sale.setCustomerEmail(dto.customerEmail);
        sale.setDate(dto.date);
        sale.setPaymentId(dto.paymentId);
        sale.setSaleId(dto.saleId);
        sale.setUserId(dto.userId);
        sale.setStatus(SaleStatusEnum.NEW);
        
        return sale;
        
    }
    
    public static SaleLineItem toSaleLineItem(SaleLineItemDto dto) {
        SaleLineItem saleLineItem = new SaleLineItem();
        
        saleLineItem.setSaleId(dto.saleId);
        saleLineItem.setInventoryId(dto.inventoryId);
        
        return saleLineItem;
    }
    
    // IBT
    
    public static IBT toIbt(IbtDto dto) {
        IBT ibt = new IBT();
        
        ibt.Id = dto.Id;
        ibt.setInventoryIdFrom(dto.inventoryIdFrom);
        ibt.setBranchIdTo(dto.branchIdTo);
        ibt.setQuantity(dto.quantity);
        ibt.setPhoneNumber(dto.phoneNumber);
        ibt.setEmailAddress(dto.emailAddress);
        ibt.setStatus(dto.status);
        
        return ibt;
    }
}
