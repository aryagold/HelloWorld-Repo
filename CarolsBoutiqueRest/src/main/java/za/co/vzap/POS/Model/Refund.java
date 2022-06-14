package za.co.vzap.POS.Model;

import za.co.vzap.Interface.Model.IEntity;
import java.sql.Timestamp;

public class Refund implements IEntity {
   public int Id;
    private String saleId;
    private Timestamp date;
    private RefundStatusEnum status;

    public Refund(String saleId, Timestamp date, RefundStatusEnum status) {
        this.saleId = saleId;
        this.date = date;
        this.status = status;
    }

    public Refund() {
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    } 
    
    public String formattedDate() {
        return this.date.toString();
    }

    public RefundStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RefundStatusEnum status) {
        this.status = status;
    }
    
    
}
