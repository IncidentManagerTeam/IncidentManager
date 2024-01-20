package com.example.incidentmanager.User.service;

import java.util.List;
import java.util.ArrayList;

import com.example.incidentmanager.User.domain.UserEntity;

public class UserServiceImpl implements UserService {

    private List<UserEntity> users = new ArrayList<>();
    private UserEntity userLogin;

    @Override
    public List<UserEntity> getAll() {
        return users;
    }

    @Override
    public void register(UserEntity user) {
        if (userExists(user.getEmail())) {
            System.out.println("El ususario ya existe");
        }else{
            users.add(user);
            System.out.println("Se ha registrado correctamente el usuario: " + user);
        }
    }

    @Override
    public void login(String email, String password) {
        for (UserEntity user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                userLogin = user;
                System.out.println("Inicio de sesión:" + user.getEmail());
            }
        }
        System.out.println("Inicio de sesión no exitoso");
    }

    @Override
    public boolean userExists(String email) {
        for (UserEntity user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserEntity me() {
        return userLogin;
    }
    
}
