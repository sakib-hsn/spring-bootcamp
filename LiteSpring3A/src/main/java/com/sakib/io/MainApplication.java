package com.sakib.io;

import com.sakib.io.controller.ProductController;
import com.sakib.io.litespring.LiteSpringApplication;
import com.sakib.io.litespring.annotation.PackageScan;
import com.sakib.io.models.Product;
import com.sakib.io.models.dto.AddProductRequest;
import com.sakib.io.models.dto.AddProductResponse;
import com.sakib.io.repository.ProductRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@PackageScan(scanPackages = {"com.sakib.io"})
public class MainApplication {
    public static void main(String[] args) throws Exception {
        LiteSpringApplication.run(MainApplication.class);
        ProductRepository productRepository = (ProductRepository)
                LiteSpringApplication.getBean("productRepository");
        ProductController productController = (ProductController)
                LiteSpringApplication.getBean("productController");

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setName("iPhone 3");
        AddProductResponse response = productController.addProduct(addProductRequest);
        System.out.println("response = " + response);
    }
}


