package com.example.security.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.service.UserService;

@RestController
public class SecurityController {
    private final UserService service;
    private final PasswordEncoder encoder;

    public SecurityController(UserService userService, PasswordEncoder encode){
        this.service = userService;
        this.encoder = encode;
    }

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity entity){
        String encodedPassword = this.encoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
        return service.create(entity);
    }
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken token){
        return token;
    }
}
