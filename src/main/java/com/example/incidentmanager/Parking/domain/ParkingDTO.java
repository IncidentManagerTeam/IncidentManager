package com.example.incidentmanager.Parking.domain;

import java.util.Date;
import io.micrometer.common.lang.NonNull;

public class ParkingDTO {
    private String licensePlate;
    @NonNull
    private int companion;
    private String state;
    private Date date;
    @NonNull
    private int user_id;
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
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
