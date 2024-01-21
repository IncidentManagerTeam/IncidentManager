package com.example.incidentmanager.User.domain;

import com.example.incidentmanager.Parking.domain.ParkingEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String course;
    private Role role;
    private String password; 
    //Relacion con la tabla parking
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "user_id")
    private ParkingEntity parkingEntinty;

    public UserEntity(int id, String name, String surname, String email, String course, Role role, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.course = course;
        this.role = role;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
