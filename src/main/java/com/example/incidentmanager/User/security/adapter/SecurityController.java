package com.example.incidentmanager.User.security.adapter;

import org.springframework.web.bind.annotation.RestController;

import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SecurityController {

    private final UserService service;

    public SecurityController(UserService service){
        this.service = service;
    }
    
    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity entity) {
        return service.create(entity);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken token){
        return token
    }
    
}
