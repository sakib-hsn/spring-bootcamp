package io.sakib.demo.example2.service;

import io.sakib.demo.example2.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchService {

    private ProductService productService;

    public SearchService(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> search(String name) {
        System.out.println("search name:" + name);
        List<Product> filterProducts = new ArrayList<>();

        List<Product> products = productService.getAllProducts();
        for(Product product : products) {
            if(product.getName().toLowerCase().contains(name)) filterProducts.add(product);
        }
        return filterProducts;
    }
}
