package za.co.vzap.Sale.Model;

public class RefundItem implements IEntity {
    public int Id;
    private int inventoryId;
    private int refundId;

    public RefundItem(int inventoryId, int refundId) {
        this.inventoryId = inventoryId;
        this.refundId = refundId;
    }

    public RefundItem() {
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }
}
