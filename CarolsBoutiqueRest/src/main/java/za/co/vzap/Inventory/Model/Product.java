package za.co.vzap.Inventory.Model;

import za.co.vzap.Sale.Model.IEntity;

public class Product implements IEntity {
    public String productId;
    private String name;
    private String barcode;
    private double price;

    public Product(String name, String barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    public Product() {
    }

    public Product(String productId, String name, String barcode, double price) {
        this.productId = productId;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
