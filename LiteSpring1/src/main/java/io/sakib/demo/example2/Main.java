package io.sakib.demo.example2;



import io.sakib.demo.example2.models.Product;
import io.sakib.demo.example2.repository.ProductRepository;
import io.sakib.demo.example2.service.ProductService;
import io.sakib.demo.example2.service.SearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Main.class);

        for(String bean: ctx.getBeanDefinitionNames()) {
            System.out.println("bean = " + bean);
        }

        ProductService productService = ctx.getBean(ProductService.class);
        SearchService searchService = ctx.getBean(SearchService.class);

        Product product1 = new Product();
        product1.setName("iPhone 1");
        String id1 = productService.addProduct(product1);
        System.out.println("id1 = " + id1);

        Product product2 = new Product();
        product2.setName("iPhone 2");
        String id2 = productService.addProduct(product2);
        System.out.println("id2 = " + id2);

        Product product3 = new Product();
        product3.setName("Pixel 5");
        String id3 = productService.addProduct(product3);
        System.out.println("id3 = " + id3);

        List<Product> filteredProducts = searchService.search("iphone");
        for(Product product: filteredProducts) {
            System.out.println("product = " + product);
        }
    }
}
