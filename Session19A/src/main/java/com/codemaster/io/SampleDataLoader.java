package com.codemaster.io;

import com.codemaster.io.models.*;
import com.codemaster.io.repository.OrderRepository;
import com.codemaster.io.repository.ProductRepository;
import com.codemaster.io.repository.TagRepository;
import com.codemaster.io.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private TagRepository tagRepository;

    public SampleDataLoader(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            UserRepository userRepository,
                            TagRepository tagRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .name("Sakib")
                .email("sakib@gmail.com")
                .role(Role.CUSTOMER)
                .address(Address.builder()
                        .road("123 Main Street")
                        .city("NY")
                        .phone("1234567")
                        .build())
                .build();

        user1 = userRepository.save(user1);

        System.out.println("user1 = " + user1);

        Tag tag1 = Tag.builder()
                .id("electronics")
                .displayNameEn("Electronics")
                .build();

        Tag tag2 = Tag.builder()
                .id("phone")
                .displayNameEn("Phone")
                .build();

        Tag tag3 = Tag.builder()
                .id("laptop")
                .displayNameEn("Laptop")
                .build();

        tagRepository.saveAll(Arrays.asList(tag1, tag2, tag3));

        Product product1 = Product.builder()
                .title("iPhone 14")
                .description("Apple product")
                .price(1100)
                .tags(Arrays.asList(
                        Tag.builder().id("phone").build(),
                        Tag.builder().id("electronics").build()
                ))
                .build();

        productRepository.save(product1);

        Product product2 = Product.builder()
                .title("Lenovo Laptop")
                .description("High end gaming laptop")
                .price(1400)
                .tags(Arrays.asList(
                        Tag.builder().id("laptop").build(),
                        Tag.builder().id("electronics").build()
                ))
                .build();

        productRepository.save(product2);

        Order order1 = Order.builder()
                .name("Phone Order")
                .totalAmount(1600)
                .user(User.builder().id(user1.getId()).build())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();

        orderRepository.save(order1);

        Order order2 = Order.builder()
                .name("Laptop Order")
                .totalAmount(2200)
                .user(User.builder().id(user1.getId()).build())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();

        orderRepository.save(order2);

        Pageable pageable = PageRequest.of(1, 1, Sort.by("id").descending());

        List<Order> orders = orderRepository.findOrderByStatus(OrderStatus.IN_PROGRESS, pageable);
        System.out.println("orders = " + orders);

        List<Product> products = productRepository.findProductsByTag("phone");
        System.out.println("products = " + products);

        List<String> productIds = productRepository.findProducIdsByTag("phone");
        System.out.println("productIds = " + productIds);

    }
}