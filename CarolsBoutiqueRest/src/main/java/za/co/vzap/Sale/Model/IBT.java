package za.co.vzap.Sale.Model;

public class IBT implements IEntity {
    public int Id;
    private String branchIdFrom;
    private String branchIdTo;
    private String productId;
    private int quantity;
    private String email;
    private IBTStatusEnum status;

    public IBT(String branchIdFrom, String branchIdTo, String productId, int quantity, String email, IBTStatusEnum status) {
        this.branchIdFrom = branchIdFrom;
        this.branchIdTo = branchIdTo;
        this.productId = productId;
        this.quantity = quantity;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IBTStatusEnum getStatus() {
        return status;
    }

    public void setStatus(IBTStatusEnum status) {
        this.status = status;
    }
}
