package za.co.vzap.Sale.Model;

public class SaleLineItem implements IEntity {
    public int Id;
    private String productId;
    private int quantity;

    public SaleLineItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public SaleLineItem() {
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
