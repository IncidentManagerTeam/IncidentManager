package com.example.incidentmanager.User.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.example.incidentmanager.User.core.UserAlreadyExistsException;
import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.domain.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private List<UserEntity> users = new ArrayList<>();
    private UserEntity userLogin;
    private final PasswordEncoder encoder;
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository,PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }


    @Override
    public Iterable<UserEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public void register(UserEntity user) {
        if (userExists(user.getEmail())) {
            System.out.println("El ususario ya existe");
        }else{
            users.add(user);
            repository.save(user);
            System.out.println("Se ha registrado correctamente el usuario: " + user);
        }
        
    }

    @Override
    public UserEntity login(String email, String password) {
        for (UserEntity user : repository.findAll()) {
            if (user.getEmail().equals(email) && this.encoder.matches(password,user.getPassword())) {
                userLogin = user;
                System.out.println("Inicio de sesión:" + user.getEmail());
                return user;
            }
        }
        System.out.println("Inicio de sesión no exitoso");
        return new UserEntity(-1, "", "", "", null, "");
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
    public UserEntity getOne(int id){
        return this.repository.findById(id).get();
    }

    @Override
    public UserEntity create(UserEntity user) {
        if (repository.existsByEmailIgnoreCase(user.getEmail())) {
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
        oldUser.setCourse(oldUser.getCourse());

        return repository.save(oldUser);
    }
    
}
