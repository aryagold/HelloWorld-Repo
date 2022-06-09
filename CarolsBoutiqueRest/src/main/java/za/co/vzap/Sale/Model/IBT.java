package za.co.vzap.Sale.Model;

public class IBT implements IEntity {
    public int Id;
    private String branchIdFrom;
    private String branchIdTo;
    private String productId;
    private int quantity;
    private String phoneNumber;
    private IBTStatusEnum status;

    public IBT(String branchIdFrom, String branchIdTo, String productId, int quantity, String phoneNumber, IBTStatusEnum status) {
        this.branchIdFrom = branchIdFrom;
        this.branchIdTo = branchIdTo;
        this.productId = productId;
        this.quantity = quantity;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public IBT() {
    }

    public String getBranchIdFrom() {
        return branchIdFrom;
    }

    public void setBranchIdFrom(String branchIdFrom) {
        this.branchIdFrom = branchIdFrom;
    }

    public String getBranchIdTo() {
        return branchIdTo;
    }

    public void setBranchIdTo(String branchIdTo) {
        this.branchIdTo = branchIdTo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public IBTStatusEnum getStatus() {
        return status;
    }

    public void setStatus(IBTStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IBT{" + "Id=" + Id + ", branchIdFrom=" + branchIdFrom + ", branchIdTo=" + branchIdTo + ", productId=" + productId + ", quantity=" + quantity + ", phoneNumber=" + phoneNumber + ", status=" + status + '}';
    }
    
    
}
