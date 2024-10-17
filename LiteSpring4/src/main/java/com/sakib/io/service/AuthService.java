package com.sakib.io.service;

import com.sakib.io.litespring.annotation.Autowired;
import com.sakib.io.litespring.annotation.Component;
import com.sakib.io.models.User;
import com.sakib.io.repository.UserRepository;

import java.util.List;

@Component
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        user = user.toBuilder()
                .username(user.getName().toLowerCase())
                .build();

        boolean success = userRepository.register(user);
        if(success) return user;
        return User.builder().build();
    }

    public User signIn(String username, String password) {
        if(userRepository.passwordMatch(username, password)) return userRepository.getUser(username);
        return User.builder().build();
    }

    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }
}
