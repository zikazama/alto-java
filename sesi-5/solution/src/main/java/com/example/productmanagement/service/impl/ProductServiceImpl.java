package com.example.productmanagement.service.impl;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.model.Category;
import com.example.productmanagement.model.Supplier;
import com.example.productmanagement.repository.ProductRepository;
import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.SupplierRepository;
import com.example.productmanagement.service.ProductService;
import com.example.productmanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    
    public ProductServiceImpl(ProductRepository productRepository, 
                              CategoryRepository categoryRepository,
                              SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }
    
    @Override
    public Product createProduct(Product product) {
        // Validasi dan logika bisnis lainnya bisa ditambahkan di sini
        Category category = categoryRepository.findByName(product.getCategory().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Supplier supplier = supplierRepository.findByName(product.getSupplier().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        product.setCategory(category);
        product.setSupplier(supplier);
        return productRepository.save(product);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        
        Category category = categoryRepository.findByName(productDetails.getCategory().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Supplier supplier = supplierRepository.findByName(productDetails.getSupplier().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        product.setCategory(category);
        product.setSupplier(supplier);
        
        return productRepository.save(product);
    }
    
    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
