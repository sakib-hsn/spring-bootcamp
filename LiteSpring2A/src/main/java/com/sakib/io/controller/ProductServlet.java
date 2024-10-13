package com.sakib.io.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakib.io.models.Product;
import com.sakib.io.models.dto.AddProductRequest;
import com.sakib.io.models.dto.AddProductResponse;
import com.sakib.io.models.dto.SearchResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class ProductServlet extends HttpServlet {

    private ProductController productController;

    public ProductServlet(ProductController productController) {
        this.productController = productController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String uri = req.getRequestURI();
        System.out.println("uri = " + uri);

        resp.setContentType("application/json");

        if (uri.contains("/search")) {
            String searchQuery = req.getParameter("query");
            System.out.println("searchQuery = " + searchQuery);
            SearchResponse searchResponse = productController.search(searchQuery);
            resp.getWriter().write(objectMapper.writeValueAsString(searchResponse));
        } else {
            String productId = extractIdFromUri(uri);
            System.out.println("productId = " + productId);
            Product product = productController.getProduct(productId);
            resp.getWriter().write(objectMapper.writeValueAsString(product));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = readJson(req);
        AddProductRequest addProductRequest = objectMapper.readValue(json, AddProductRequest.class);
        AddProductResponse addProductResponse = productController.addProduct(addProductRequest);

        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(addProductResponse));
    }

    private String extractIdFromUri(String uri) {
        String[] parts = uri.split("/");
        return parts[parts.length - 1];
    }

    private String readJson(HttpServletRequest req) {
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        try {
            BufferedReader bufferedReader = req.getReader();
            while ((line = bufferedReader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonBuilder.toString();
    }
}
