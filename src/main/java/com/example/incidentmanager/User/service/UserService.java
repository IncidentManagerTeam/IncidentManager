package com.example.incidentmanager.User.service;

import java.util.List;

import com.example.incidentmanager.User.domain.UserEntity;



public interface UserService {

    public List<UserEntity> getAll();

    public UserEntity create(UserEntity user);

    public UserEntity update(int id, UserEntity user);

    public void delete(int id);

    public void register(UserEntity user);

    public void login(String email, String password);

    public boolean userExists(String email);

    public UserEntity me();

}
