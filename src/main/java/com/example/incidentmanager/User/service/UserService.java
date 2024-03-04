package com.example.incidentmanager.User.service;


import java.util.Optional;

import com.example.incidentmanager.User.domain.User;
import com.example.incidentmanager.User.domain.UserEntity;



public interface UserService {

    public Iterable<UserEntity> getAll();

    public UserEntity create(UserEntity user);

    public UserEntity update(int id, UserEntity user);

    public void delete(int id);

    public void register(UserEntity user);

    public UserEntity login(String email, String password);

    public boolean userExists(String email);

    public UserEntity me();

    public UserEntity getOne(int id);

}
