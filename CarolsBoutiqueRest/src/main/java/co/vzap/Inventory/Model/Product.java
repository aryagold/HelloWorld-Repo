package co.vzap.Inventory.Model;

import co.vzap.Sale.Model.IEntity;

public class Product implements IEntity {
        public String productId;
    private String name;
    private long barcode;
    private String size;
    private long price;

    public Product(String name, long barcode, String size, long price) {
        this.name = name;
        this.barcode = barcode;
        this.size = size;
        this.price = price;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
