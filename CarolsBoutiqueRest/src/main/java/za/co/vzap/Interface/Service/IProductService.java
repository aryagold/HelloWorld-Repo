package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.ProductDto;

public interface IProductService {
    ProductDto addProduct(String productName, double price, List<String> categoryIds);

    List<ProductDto> getAllProducts();
    
    List<Category> getAllCategories();

    String addCategory(Category category);
}
