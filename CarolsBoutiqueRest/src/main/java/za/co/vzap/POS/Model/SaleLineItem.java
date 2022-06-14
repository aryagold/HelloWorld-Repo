package za.co.vzap.POS.Model;

import za.co.vzap.Interface.Model.IEntity;

public class SaleLineItem implements IEntity {
    public int Id;
    private int inventoryId;
    private String saleId;

    public SaleLineItem(String saleId, int inventoryId) {
        this.saleId = saleId;
        this.inventoryId = inventoryId;
        
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

    @Override
    public String toString() {
        return "SaleLineItem{" + "Id=" + Id + ", inventoryId=" + inventoryId + ", saleId=" + saleId + '}';
    }
    
    

   
}
