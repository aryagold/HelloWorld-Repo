package za.co.vzap.Model.Sale;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RefundDto {
    public int Id;
    public String saleId;
    public Timestamp date;
    
    public List<RefundItemDto> refundItems = new ArrayList<>();
    
}
