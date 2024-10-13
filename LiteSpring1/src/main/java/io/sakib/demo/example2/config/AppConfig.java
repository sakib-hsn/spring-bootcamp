package io.sakib.demo.example2.config;

import io.sakib.demo.example1.models.Product;
import io.sakib.demo.example2.service.ProductService;
import io.sakib.demo.example2.service.SearchService;
import io.sakib.demo.example2.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    // can be replaced by Component/Repository/Service
    @Bean()
    public ProductRepository productRepository() {
        System.out.println("productRepository call.");
        return new ProductRepository();
    }

    @Bean
    public ProductService productService() {
        System.out.println("productService call.");
        return new ProductService(productRepository());
    }

    @Bean
    public SearchService searchService() {
        System.out.println("searchService call.");
        return new SearchService(productService());
    }
}
