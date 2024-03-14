package com.example.incidentmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.service.UserService;

@SpringBootApplication
public class IncidentManagerApplication implements CommandLineRunner {
private final UserService userSvc;
public IncidentManagerApplication(UserService userService){
	this.userSvc = userService;
}
	public static void main(String[] args) {
		SpringApplication.run(IncidentManagerApplication.class, args);
	}
@Override
public void run(String... args)throws Exception{
	UserEntity user = new UserEntity(0, "", "null", "null", null, "null");
	try {
		this.userSvc.create(user);		
	} catch (Exception e) { 
	}
}
}
