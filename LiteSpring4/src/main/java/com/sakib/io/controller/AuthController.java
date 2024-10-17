package com.sakib.io.controller;

import com.sakib.io.litespring.MethodType;
import com.sakib.io.litespring.annotation.*;
import com.sakib.io.litespring.annotation.*;
import com.sakib.io.models.User;
import com.sakib.io.models.dto.LoginRequest;
import com.sakib.io.models.dto.RegisterRequest;
import com.sakib.io.models.dto.RegisterResponse;
import com.sakib.io.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(url = "/api/register", type = MethodType.POST)
    User login(@RequestBody LoginRequest request) {
        return authService.signIn(request.getUsername(), request.getPassword());
    }

    @RequestMapping(url = "/api/user/login", type = MethodType.POST)
    RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .password(registerRequest.getPassword())
                .build();
        user = authService.register(user);
        return RegisterResponse.builder()
                .user(user)
                .build();
    }

    @Authenticated
    @RequestMapping(url = "/api/user/{id}", type = MethodType.GET)
    User getUser(@PathVariable(value = "id") String id) {
        return authService.getUser(id);
    }

}
