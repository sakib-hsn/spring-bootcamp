package com.sakib.io.controller;

import com.sakib.io.models.Product;
import com.sakib.io.service.ProductService;
import com.sakib.io.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchService searchService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index"; // This will point to index.html
    }

    @GetMapping("/search")
    public String search(@RequestParam String query ,Model model){
        List<Product> products = searchService.search(query);
        model.addAttribute("products", products);
        return "search";
    }
}
