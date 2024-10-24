package io.sakib.demo.example2.repository;

import io.sakib.demo.example2.models.Product;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private Map<String, Product> productMap;

    @Value("${max.size}")
    private int MAX_SIZE;

    public ProductRepository() {
        productMap = new HashMap<>();
    }

    public boolean addProduct(Product product) {
        if(productMap.containsKey(product.getId())) return false;
        if(productMap.size() >= MAX_SIZE) {
            return false;
        }
        productMap.put(product.getId(), product);
        return true;
    }

    public Product getProduct(String productId) {
        return productMap.get(productId);
    }

    public List<Product> getProducts() {
        return (new ArrayList<>(productMap.values()));
    }
}
