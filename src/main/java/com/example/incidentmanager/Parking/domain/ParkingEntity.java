package com.example.incidentmanager.Parking.domain;

import java.util.Date;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.example.incidentmanager.User.domain.UserEntity;

// import com.example.incidentmanager.User.domain.UserEntity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "parking")
public class ParkingEntity {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(unique = true, updatable = true)
    private String licensePlate;

    @NonNull
    private int companion;

    private String state;

    private Date date;

    @OneToOne()
    UserEntity user;

   

    public ParkingEntity(String licensePlate, int companion, String state, Date date,UserEntity user) {
        this.licensePlate = licensePlate;
        this.companion = companion;
        this.date = date;
        this.state = state;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getCompanion() {
        return companion;
    }

    public void setCompanion(int companion) {
        this.companion = companion;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public UserEntity getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
