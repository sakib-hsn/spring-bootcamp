package com.sakib.io.service;

import com.sakib.io.litespring.annotation.Autowired;
import com.sakib.io.litespring.annotation.Component;
import com.sakib.io.models.Product;
import com.sakib.io.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String addProduct(Product product) {
        String id = UUID.randomUUID().toString();
        product.setId(id);
        boolean success = productRepository.addProduct(product);
        if(success) return id;
        return "";
    }

    public Product getProduct(String id) {
        if(id == null) return null;
        return productRepository.getProduct(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }
}
