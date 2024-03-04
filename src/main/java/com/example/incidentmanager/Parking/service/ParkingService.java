package com.example.incidentmanager.Parking.service;

import java.util.Date;

import com.example.incidentmanager.Parking.domain.ParkingDTO;
import com.example.incidentmanager.Parking.domain.ParkingEntity;

public interface ParkingService {

    public Iterable<ParkingEntity> getAll();

    public ParkingEntity getOne(int id);

    public ParkingEntity update(int id,String licensePlate, int companion, String state, Date date);

    public void delete(int id);

    public ParkingEntity create(ParkingDTO parking);
}
