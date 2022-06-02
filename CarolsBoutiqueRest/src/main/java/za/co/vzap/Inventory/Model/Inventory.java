package za.co.vzap.Inventory.Model;

import za.co.vzap.Sale.Model.IEntity;

public class Inventory implements IEntity {
    public int Id;
    private String branchId;
    private int sizeId;
    private int productCode;
    private String barcode;
    private int quantity;

    public Inventory(String branchId, int sizeId, int productCode, String barcode, int quantity) {
        this.branchId = branchId;
        this.sizeId = sizeId;
        this.productCode = productCode;
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

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
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
