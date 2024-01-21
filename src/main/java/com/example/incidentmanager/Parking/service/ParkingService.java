package com.example.incidentmanager.Parking.service;

import java.util.Date;
import java.util.List;

import com.example.incidentmanager.Parking.domain.ParkingEntity;
import com.example.incidentmanager.User.domain.UserEntity;

public interface ParkingService {

    public List<ParkingEntity> getAll();

    public ParkingEntity getOne(int id);

    public ParkingEntity update(int id,String licensePlate, int companion, String state, Date date);

    public void delete(int id);

    public ParkingEntity create(UserEntity user, String licensePlate, int companion, String state, Date date);
}
