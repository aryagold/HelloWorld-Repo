package za.co.vzap.Product.Service;

import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Inventory.Model.ProductCategory;
import za.co.vzap.Inventory.Model.ProductDto;

public class ProductService implements IProductService {
    private IRepository productRepository = null;
    private IRepository productCategoryRepository = null;
    private IRepository categoryRepository = null;

    public ProductService(IRepository productRepository, IRepository productCategoryRepository, IRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto addProduct(String productName, double price, List<String> categoryIds) {
        Product product = new Product(productName, price);
        String id = productRepository.add2(product);

        for (String catId : categoryIds) {
            productCategoryRepository.add(new ProductCategory(product.getProductId(), catId));
        }

        return toProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> dtos = new ArrayList<>();

        List<Product> products = productRepository.getAll();

        for (Product product : products) {
            dtos.add(toProductDto(product));
        }

        return dtos;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAll();
    }

    @Override
    public String addCategory(Category category) {
        String id = categoryRepository.add2(category);

        return id + " has been added to the category catalogue.";
    }

    private ProductDto toProductDto(Product product) {
        ProductDto dto = new ProductDto();

        dto.productId = product.productId;
        dto.name = product.getName();
        dto.price = product.getPrice();

        List<ProductCategory> productCategories = productCategoryRepository.getWhere("productId", product.productId);

        for (ProductCategory productCategory : productCategories) {
            Category category = (Category) categoryRepository.getById(productCategory.getCategoryId());

            dto.categories.add(category.getName());
        }

        return dto;
    }
}
