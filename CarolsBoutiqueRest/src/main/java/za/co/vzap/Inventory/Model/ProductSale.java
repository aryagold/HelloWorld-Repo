package za.co.vzap.Inventory.Model;

import za.co.vzap.Sale.Model.IEntity;

public class ProductSale implements IEntity {
    public int Id;
    private String productId;
    private String saleId;

    public ProductSale(String productId, String saleId) {
        this.productId = productId;
        this.saleId = saleId;
    }

    public ProductSale() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }
}
