package com.sakib.io.controller;


import com.sakib.io.litespring.annotation.*;
import com.sakib.io.litespring.enums.MethodType;
import com.sakib.io.litespring.annotation.*;
import com.sakib.io.models.Product;
import com.sakib.io.models.dto.SearchResponse;
import com.sakib.io.service.ProductService;
import com.sakib.io.service.SearchService;

import java.util.List;

@Component
@RestController
public class SearchController {
    @Autowired
    private ProductService productService;

    @Autowired
    private SearchService searchService;

    @RequestMapping(url = "/api/search", type = MethodType.GET)
    public SearchResponse search(@RequestParam("query") String query) {
        List<Product> productList = searchService.search(query);
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setProducts(productList);
        return searchResponse;
    }
}
