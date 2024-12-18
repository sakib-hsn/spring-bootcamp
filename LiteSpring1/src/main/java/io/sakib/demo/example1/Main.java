package io.sakib.demo.example1;


import io.sakib.demo.example1.models.Product;
import io.sakib.demo.example1.repository.ProductRepository;
import io.sakib.demo.example1.service.ProductService;
import io.sakib.demo.example1.service.SearchService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        SearchService searchService = new SearchService(productService);

        Product product1 = Product.builder()
                .name("iPhone 16")
                .build();
        String id1 = productService.addProduct(product1);
        System.out.println("id1 = " + id1);

        Product product2 = Product.builder()
                .name("Pixel 5")
                .build();
        String id2 = productService.addProduct(product2);
        System.out.println("id2 = " + id2);

        Product product3 = Product.builder()
                .name("iPhone 15")
                .build();
        String id3 = productService.addProduct(product3);
        System.out.println("id3 = " + id3);

        List<Product> filterResults = searchService.search("pixel");
        for(Product product : filterResults) {
            System.out.println("product = " + product);
        }


    }
}
