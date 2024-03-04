package com.example.incidentmanager.Incident.domain;

import com.example.incidentmanager.User.domain.UserEntity;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

public class IncidentEntity {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;

    @NonNull
    private String description;

    @NonNull
    private String state;

    @OneToOne
    private UserEntity user;


    public IncidentEntity(int id, byte[] image, String description, String state, UserEntity user) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.state = state;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public byte[] getImage(){
        return image;
    }

    public void setImage(byte[] image){
        this.image = image;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserEntity getUser() {
        return user;
    }
}
