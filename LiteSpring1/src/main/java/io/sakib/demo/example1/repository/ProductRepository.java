package io.sakib.demo.example1.repository;

import io.sakib.demo.example1.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private Map<String, Product> map;

    public ProductRepository() {
        map = new HashMap<>();
    }

    public boolean addProduct(Product product) {
        if(map.containsKey(product.getId())) return false;
        map.put(product.getId(), product);
        return true;
    }

    public Product getProduct(String productId) {
        return map.get(productId);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(map.values());
    }
}
