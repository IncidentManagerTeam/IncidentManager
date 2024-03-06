package com.example.incidentmanager.User.security.adapter;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;

import com.example.incidentmanager.User.domain.User;
import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class SecurityController {

    private final UserService service;
    private final PasswordEncoder encoder;

    public SecurityController(UserService service, PasswordEncoder encoder){
        this.service = service;
        this.encoder = encoder;
    }
    
    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity entity) {
        String encodedPassword = this.encoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
        return service.create(entity);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken token){
        return token;
    }
    @PostMapping("/login")
    public UserEntity login(@RequestBody User entity) {
        return service.login(entity.getEmail(),entity.getPassword());
    }
    
}
