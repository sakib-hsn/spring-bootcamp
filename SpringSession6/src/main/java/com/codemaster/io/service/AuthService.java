package com.codemaster.io.service;

import com.codemaster.io.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private AuthService(UserService userService) {
        this.userService = userService;
    }

    public Authentication passwordMatch(String email, String password) {
        System.out.println("email = " + email);
        System.out.println("password = " + password);
        Authentication reqAuthentication = new UsernamePasswordAuthenticationToken(email, password);
        System.out.println("reqAuthentication = " + reqAuthentication);
        return authenticationManager.authenticate(reqAuthentication);
    }

    public long signup(User user) {
        return userService.addUser(user);
    }

}
