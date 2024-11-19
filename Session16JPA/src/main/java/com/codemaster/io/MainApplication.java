package com.codemaster.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
//        Car realCar = new RealCar();
//
//        // Interface Car
//        // Object realCar
//        // ClassLoader Car
//
//        Car proxy = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),
//                new Class<?>[]{Car.class}, new LogAdder(realCar));
//
//        proxy.startEngine();
//        proxy.stopEngine();
//        proxy.makeSound();

        ApplicationContext ctx = SpringApplication.run(MainApplication.class);
//        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);


        User user = User.builder()
                .id(1)
                .name("Admin")
                .build();

//        UserRepository userRepository = (UserRepository) RepositoryProxy
//                .getProxyInstance(UserRepository.class, User.class, jdbcTemplate);
//
////        userRepository.insert(user);
//        User user1 = userRepository.getByName("Admin");
//        System.out.println("user1 = " + user1);

        UserRepository userRepository = ctx.getBean(UserRepository.class);

        userRepository.save(user);
//        Optional<User> user1 = userRepository.findById(1);
        Optional<User> user1 = userRepository.findByName("Admin");
        System.out.println("user1 = " + user1);


//        ProductRepository productRepository = (ProductRepository) RepositoryProxy
//                .getProxyInstance(ProductRepository.class, Product.class, jdbcTemplate);

        Product product = Product.builder()
                .id(1)
                .title("iPhone")
                .build();
////        productRepository.insert(product);
//        Product product1 = productRepository.getByTitle("iphone");
//        System.out.println("product1 = " + product1);

        ProductRepository productRepository = ctx.getBean(ProductRepository.class);

        productRepository.save(product);
        Optional<Product> product1 = productRepository.findById(1);
        System.out.println("product1 = " + product1);


    }
}