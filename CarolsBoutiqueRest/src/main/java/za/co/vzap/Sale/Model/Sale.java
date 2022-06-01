package za.co.vzap.Sale.Model;

import java.sql.Timestamp;

public class Sale implements IEntity {
    public String saleId;
    private String userId;
    private String email;
    private Timestamp date;
    private int paymentId;
    private String branchId;
    private SaleStatusEnum status;

    public Sale(String userId, String email, Timestamp date, int paymentId, String branchId, SaleStatusEnum status) {
        this.userId = userId;
        this.email = email;
        this.date = date;
        this.paymentId = paymentId;
        this.branchId = branchId;
        this.status = status;
    }

    public Sale(String saleId, String userId, String email, Timestamp date, int paymentId, String branchId, SaleStatusEnum status) {
        this.saleId = saleId;
        this.userId = userId;
        this.email = email;
        this.date = date;
        this.paymentId = paymentId;
        this.branchId = branchId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public SaleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SaleStatusEnum status) {
        this.status = status;
    }
}
