package za.co.vzap.Model.Sale;

import java.sql.Timestamp;
import za.co.vzap.Interface.Model.IEntity;
import za.co.vzap.Interface.Model.IEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Refund implements IEntity {

    public int Id;
    private String saleId;
    private Timestamp date;
    private RefundStatusEnum status;

    public List<RefundItem> items = new ArrayList<>();

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
