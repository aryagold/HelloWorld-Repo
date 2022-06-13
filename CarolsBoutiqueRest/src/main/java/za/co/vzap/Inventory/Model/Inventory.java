package za.co.vzap.Inventory.Model;

import za.co.vzap.Interface.Model.IEntity;

public class Inventory implements IEntity {
    public int Id;
    private String branchId;
    private int sizeId;
    private String productId;
    private String barcode;
    private int quantity;

    public Inventory(String branchId, int sizeId, String productId, String barcode, int quantity) {
        this.branchId = branchId;
        this.sizeId = sizeId;
        this.productId = productId;
        this.barcode = barcode;
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

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
