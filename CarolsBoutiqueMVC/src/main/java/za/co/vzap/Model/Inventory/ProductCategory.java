package za.co.vzap.Model.Inventory;

import za.co.vzap.Interface.Model.IEntity;

public class ProductCategory implements IEntity {

    public int Id;
    private String productId;
    private String categoryId;

    public ProductCategory(String productId, String categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public ProductCategory() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}
