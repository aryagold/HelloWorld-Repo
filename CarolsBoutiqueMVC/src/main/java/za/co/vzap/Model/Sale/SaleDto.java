package za.co.vzap.Model.Sale;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SaleDto {
    public String saleId;
    public String userId;
    public String customerEmail;
    public Timestamp date;
    public Integer paymentId;
    public SaleStatusEnum status;
    
    public List<SaleLineItemDto> lineitems = new ArrayList<>();
    
    private double total;

    public double getTotal() {
        for(SaleLineItemDto item : lineitems) {
            total += item.price;
        } 
        return total;
    }
}
