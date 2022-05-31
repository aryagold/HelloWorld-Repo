package co.vzap.Inventory.Model;

import co.vzap.Sale.Model.IEntity;

public class Product implements IEntity {
    public String productId;
    private String name;
    private String barcode;
    private String size;
    private double price;

    public Product(String name, String barcode, String size, double price) {
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
