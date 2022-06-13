package za.co.vzap.Inventory.Model;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    public String productId;
    public String name;
    public double price;
    
    public List<String> categories;

    public ProductDto() {
        categories = new ArrayList<>();
    }
}
