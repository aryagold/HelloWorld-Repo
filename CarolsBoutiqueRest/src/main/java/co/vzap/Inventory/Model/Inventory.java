package co.vzap.Inventory.Model;

import co.vzap.Sale.Model.IEntity;

public class Inventory implements IEntity {
    public int Id;
    private String branchId;
    private String productId;
    private int quantity;

    public Inventory(String branchId, String productId, int quantity) {
        this.branchId = branchId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Inventory() {
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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
}
