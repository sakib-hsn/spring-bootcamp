package com.sakib.io;

import com.sakib.io.controller.HomeServlet;
import com.sakib.io.controller.ProductController;
import com.sakib.io.controller.ProductServlet;
import com.sakib.io.repository.ProductRepository;
import com.sakib.io.service.ProductService;
import com.sakib.io.service.SearchService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class MainApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir("temp");

        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);

        HomeServlet homeServlet = new HomeServlet();
        tomcat.addServlet(contextPath, "homeServlet", homeServlet);
        context.addServletMappingDecoded("/", "homeServlet");

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        SearchService searchService = new SearchService(productService);
        ProductController productController = new ProductController(productService, searchService);
        ProductServlet productServlet = new ProductServlet(productController);

        tomcat.addServlet(contextPath, "productServlet", productServlet);
        context.addServletMappingDecoded("/api/products/*", "productServlet");

        tomcat.start();
        tomcat.getServer().await();

    }
}


