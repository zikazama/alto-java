package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> searchProductsByName(String name);
    List<Product> getProductsByCategory(String categoryName);
    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);
}
