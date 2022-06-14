package za.co.vzap.Model.Inventory;

import java.sql.Timestamp;
import za.co.vzap.Interface.Model.IEntity;

public class InventoryControl implements IEntity {
    public int Id;
    private String userId;
    private int inventoryId;
    private Timestamp date;
    private int quantityBefore;
    private int incomingQuantity;
    private int newStockQuantity;
    private boolean posted;

    public InventoryControl(String userId, int inventoryId, Timestamp date, int quantityBefore, int incomingQuantity, int newStockQuantity, boolean posted) {
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.date = date;
        this.quantityBefore = quantityBefore;
        this.incomingQuantity = incomingQuantity;
        this.newStockQuantity = newStockQuantity;
        this.posted = posted;
    }

    public InventoryControl() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getQuantityBefore() {
        return quantityBefore;
    }

    public void setQuantityBefore(int quantityBefore) {
        this.quantityBefore = quantityBefore;
    }

    public int getIncomingQuantity() {
        return incomingQuantity;
    }

    public void setIncomingQuantity(int incomingQuantity) {
        this.incomingQuantity = incomingQuantity;
    }

    public int getNewStockQuantity() {
        return newStockQuantity;
    }

    public void setNewStockQuantity(int newStockQuantity) {
        this.newStockQuantity = newStockQuantity;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }
}
