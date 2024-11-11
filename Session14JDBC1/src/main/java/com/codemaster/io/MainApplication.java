package com.codemaster.io;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User user = User.builder()
                    .name("user28dfakfjalso;ifjhljkadshflkjadh")
                    .email("user28@gmail.com")
                    .age(30)
                    .country("BD")
                    .build();

            userRepository.insertUserAndAuditData(user);
//            System.out.println("id = " + id);

//            user = userRepository.getUser(id);
//            System.out.println("user = " + user);
//
//            boolean success = userRepository.deleteUser(id);
//            System.out.println("success = " + success);
//
//            List<User> userList = userRepository.getUsers();
//            System.out.println("userList = " + userList);
        };
    }
}
