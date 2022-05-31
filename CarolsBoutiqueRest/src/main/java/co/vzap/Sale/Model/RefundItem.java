package co.vzap.Sale.Model;

public class RefundItem implements IEntity {
    public int Id;
    private String productId;
    private int quantity;

    public RefundItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public RefundItem() {
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
