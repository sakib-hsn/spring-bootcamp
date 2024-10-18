package com.codemaster.io.service;

import com.codemaster.io.litespring.annotation.Autowired;
import com.codemaster.io.litespring.annotation.Component;
import com.codemaster.io.models.User;
import com.codemaster.io.repository.UserRepository;
import com.fasterxml.jackson.core.PrettyPrinter;

@Component
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public boolean register(User user) {
        return userRepository.register(user);
    }

    public User login(String username, String password) {
        if(userRepository.passwordMatch(username, password))
            return userRepository.getUser(username);
        return null;
    }

    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public boolean deleteUser(String username) {
        return userRepository.deleteUser(username);
    }

}
