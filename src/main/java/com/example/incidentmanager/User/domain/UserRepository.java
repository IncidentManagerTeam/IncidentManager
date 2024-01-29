package com.example.incidentmanager.User.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository <UserEntity, Integer> {
    //public Iterable<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByEmailIgnoreCase(String email);
    public boolean existsByEmailIgnoreCase(String email);
}
