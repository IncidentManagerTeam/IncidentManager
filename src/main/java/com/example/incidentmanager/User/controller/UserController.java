package com.example.incidentmanager.User.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.incidentmanager.User.core.UserAlreadyExistsException;
import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.service.UserService;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.NoSuchElementException;




@RestController
public class UserController {
    
    public UserController(UserService service){
        this.service = service;
    }

    private UserService service;

    @GetMapping("api/users")
    public Iterable<UserEntity> getAllUser() {
        return this.service.getAll();
    }


    @PostMapping("api/users")
    public UserEntity createUser(@RequestBody UserEntity user){
        UserEntity newUser;
        try {
            newUser = service.create(user);
        } catch (UserAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Ya exist el correo para otro usuario");
        }
        return newUser;
    }

    @DeleteMapping("api/users/{id}")
    public void deletUser(@PathVariable int id){
        service.delete(id);
    }

    @PutMapping("api/users/{id}")
    public UserEntity putMethodName(@PathVariable int id, @RequestBody UserEntity user) { 
        try {
            return service.update(id, user);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }       
    }
}
