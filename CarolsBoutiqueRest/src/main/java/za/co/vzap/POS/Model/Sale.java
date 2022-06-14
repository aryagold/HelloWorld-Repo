package za.co.vzap.POS.Model;

import za.co.vzap.Interface.Model.IEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Sale implements IEntity {
    public String saleId;
    private String userId;
    private String customerEmail;
    private Timestamp date;
    private Integer paymentId;
    private SaleStatusEnum status;
    
    public List<SaleLineItem> items = new ArrayList<>();

    public Sale(String userId, String customerEmail, Timestamp date, Integer paymentId, SaleStatusEnum status) {
        this.userId = userId;
        this.customerEmail = customerEmail;
        this.date = date;
        this.paymentId = paymentId;
        this.status = status;
    }

    public Sale(String saleId, String userId, String customerEmail, Timestamp date, Integer paymentId, SaleStatusEnum status) {
        this.saleId = saleId;
        this.userId = userId;
        this.customerEmail = customerEmail;
        this.date = date;
        this.paymentId = paymentId;
        this.status = status;
    }
    
    

    public Sale() {
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public SaleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SaleStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sale{" + "saleId=" + saleId + ", userId=" + userId + ", customerEmail=" + customerEmail + ", date=" + date + ", paymentId=" + paymentId + ", status=" + status + '}';
    }
    
    
}
