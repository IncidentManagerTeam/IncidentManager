package com.example.incidentmanager.Incident.domain;

import com.example.incidentmanager.User.domain.UserEntity;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name="incident")
public class IncidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @NonNull
    private String description;

    private String state;

    @ManyToOne
    private UserEntity user;

    @NonNull
    private String classroom;

   
    @NonNull
    private String title;

    public IncidentEntity(){
        
    }
    public IncidentEntity( byte[] image, String description, String state, UserEntity user, String classroom, String title) {
        this.image = image;
        this.description = description;
        this.state = state;
        this.user = user;
        this.classroom = classroom;
        this.title = title;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
