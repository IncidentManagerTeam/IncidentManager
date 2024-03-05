package com.example.incidentmanager.Incident.domain;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.OneToOne;

public class IncidentDTO {

    @NonNull
    private String description;

    
    private String state;

    @NonNull
    private String classroom;

    @NonNull
    private String title;

    private int user_id;

    public IncidentDTO(String description, String state, int userId, String classroom, String title) {
        this.description = description;
        this.state = state;
        this.user_id = userId;
        this.classroom = classroom;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int id) {
        this.user_id = id;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String _classroom) {
        this.classroom = _classroom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
