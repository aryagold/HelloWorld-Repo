package za.co.vzap.Sale.Model;

public class SaleLineItem implements IEntity {
    public int Id;
    private int inventoryId;
    private String saleId;

    public SaleLineItem(String saleId, int inventoryId) {
        this.inventoryId = inventoryId;
        this.saleId = saleId;
    }

    public SaleLineItem() {
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }
    
    

   
}
