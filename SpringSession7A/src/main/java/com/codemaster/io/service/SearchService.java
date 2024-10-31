package com.codemaster.io.service;

import com.codemaster.io.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchService {

    private ProductService productService;

    public SearchService(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> search(String name) {
        List<Product> filterProducts = new ArrayList<>();

        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                filterProducts.add(product);
            }
        }
        return filterProducts;
    }
}
