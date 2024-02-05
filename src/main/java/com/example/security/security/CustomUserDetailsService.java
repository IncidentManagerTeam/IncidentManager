package com.example.security.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.domain.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository repository;
    public CustomUserDetailsService(UserRepository repository){
        this.repository = repository;
    }

    // Esto es para devolver el usuario 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userEntity = this.repository.findByEmailIgnoreCase(username).orElseThrow(
        () -> new UsernameNotFoundException(username)
       );
       UserDetails userDetails = User.builder()
                                        .username(userEntity.getEmail())
                                        .password(userEntity.getPassword())
                                        .roles("ADMIN")
                                        .build();
        return userDetails;
    }
    
}
