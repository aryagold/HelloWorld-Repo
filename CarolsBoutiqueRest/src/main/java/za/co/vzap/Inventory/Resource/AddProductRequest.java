package za.co.vzap.Inventory.Resource;

import java.util.ArrayList;
import java.util.List;

public class AddProductRequest {
    public String name;
    public double price;
    
    public List<String> categoryIds;

    public AddProductRequest() {
        this.categoryIds = new ArrayList<>();
    }
    
    
}
