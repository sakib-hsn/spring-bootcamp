package com.codemaster.io.service;

import com.codemaster.io.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

// to ensure spring can create bean with 'ACLService' name, eta na dileo ei nam ei create korbe. jekono nam dileo hobr
@Service("ACLService")
public class ACLService {
    @Autowired
    private ProductService productService;

    public boolean hasPermitToDelete(long productId) {
        System.out.println("productId = " + productId);
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Product product = productService.getProduct(productId);
        if (product == null) {
            return false;
        }
        return product.getCreatedByUserEmail().equals(userEmail);
    }
}
