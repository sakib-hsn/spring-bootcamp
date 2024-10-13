package com.sakib.io.controller;

import com.sakib.io.models.Product;
import com.sakib.io.models.dto.AddProductRequest;
import com.sakib.io.models.dto.AddProductResponse;
import com.sakib.io.models.dto.SearchResponse;
import com.sakib.io.service.ProductService;
import com.sakib.io.service.SearchService;

import java.util.List;

public class ProductController {
    private ProductService productService;
    private SearchService searchService;

    public ProductController(ProductService productService, SearchService searchService) {
        this.productService = productService;
        this.searchService = searchService;
    }

    public AddProductResponse addProduct(AddProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());

        String id = productService.addProduct(product);

        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setId(id);

        return addProductResponse;
    }

    public Product getProduct(String id) {
        return productService.getProduct(id);
    }

    public SearchResponse search(String name) {
        List<Product> productList = searchService.search(name);
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setProducts(productList);
        return searchResponse;
    }
}
