package za.co.vzap.Inventory.Model;

import za.co.vzap.Sale.Model.IEntity;

public class ProductCode implements IEntity {
    public int Id;
    private String productId;

    public ProductCode(String productId) {
        this.productId = productId;
    }

    public ProductCode() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductCode(String productId) {
        this.productId = productId;
    }
    
}
