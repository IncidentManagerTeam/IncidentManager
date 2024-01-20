package com.example.incidentmanager.User.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.service.UserService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class UserController {
    
    public UserController(UserService service){
        this.service = service;
    }

    private UserService service;

    @GetMapping("users")
    public List<UserEntity> getAllUser() {
        return this.service.getAll();
    }
    
    @GetMapping("/profile")
    public String userProfile(Model model){
        // obtengo los datos del usuario logueado
        UserEntity usuarioLogueado = service.me();
        // Agrega el usuario al modelo para que pueda ser mostrado en la vista
        model.addAttribute("user", usuarioLogueado);
        // Retorna el nombre de la vista (puede ser una página HTML o JSP)
        return "userProfile";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") UserEntity user) {
        service.register(user);
        return "redirect:/users";
    }
    


}
