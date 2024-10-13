package io.sakib.demo.custom_annotation;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Customer customer = new Customer("Sakib", 100);

        System.out.println("customer = " + customer);

        // Process annotations to modify the original object
        Customer updatedCustomer = (Customer) AnnotationProcessor.process(customer);

        System.out.println("updatedCustomer = " + updatedCustomer);


        Product product = new Product("1", 11);

        Product updatedProduct = (Product) AnnotationProcessor.process(product);
        System.out.println("updatedProduct = " + updatedProduct);
    }
}
