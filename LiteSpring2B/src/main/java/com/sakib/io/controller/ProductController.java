package com.sakib.io.controller;

import com.sakib.io.models.Product;
import com.sakib.io.models.dto.AddProductRequest;
import com.sakib.io.models.dto.AddProductResponse;
import com.sakib.io.models.dto.SearchResponse;
import com.sakib.io.service.ProductService;
import com.sakib.io.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchService searchService;

    @PostMapping( "/products")
    public AddProductResponse addProduct(@RequestBody AddProductRequest request) {
        System.out.println("request = " + request);
        Product product = new Product();
        product.setName(request.getName());
        String productId = productService.addProduct(product);
        System.out.println("productId = " + productId);
        AddProductResponse response = new AddProductResponse();
        response.setId(productId);
        return response;
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable String productId) {
        System.out.println("productId = " + productId);
        Product product = productService.getProduct(productId);
        if(product == null) throw new RuntimeException("Not found");
        return product;
    }

    @GetMapping("/products/search")
    public SearchResponse searchProducts(@RequestParam(required = true) String query) {
        System.out.println("query = " + query);
        List<Product> products = searchService.search(query);
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setProducts(products);
        return searchResponse;
    }

}
