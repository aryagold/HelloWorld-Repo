package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Model.Inventory.Category;
import za.co.vzap.Model.Inventory.ProductDto;

public interface IProductService {
    ProductDto addProduct(String productName, double price, List<String> categoryIds);

    List<ProductDto> getAllProducts();
    
    List<Category> getAllCategories();

    String addCategory(Category category);
}
