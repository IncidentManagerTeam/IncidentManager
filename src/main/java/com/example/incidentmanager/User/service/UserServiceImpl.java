package com.example.incidentmanager.User.service;

import java.util.List;
import java.util.ArrayList;

import com.example.incidentmanager.User.core.UserAlreadyExistsException;
import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.domain.UserRepository;

public class UserServiceImpl implements UserService {

    private List<UserEntity> users = new ArrayList<>();
    private UserEntity userLogin;

    private UserRepository repository;

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

    @Override
    public UserEntity create(UserEntity user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UserEntity update(int id, UserEntity user) {
        // como si fuera un try catch si encuenta el usuario devuelve ese usuario, si no salta el else throw
        UserEntity oldUser = repository.findById(id).orElseThrow();

        oldUser.setId(user.getId());
        oldUser.setName(user.getName());
        oldUser.setSurname(user.getSurname());
        oldUser.setRole(user.getRole());
        oldUser.setCourse(oldUser.getCourse());

        return repository.save(oldUser);
    }
    
}
