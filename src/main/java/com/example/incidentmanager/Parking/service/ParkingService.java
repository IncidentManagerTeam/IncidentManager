package com.example.incidentmanager.Parking.service;

import com.example.incidentmanager.Parking.domain.ParkingDTO;
import com.example.incidentmanager.Parking.domain.ParkingEntity;

public interface ParkingService {

    public Iterable<ParkingEntity> getAll();

    public ParkingEntity getOne(int id);

    public ParkingEntity update(int id,ParkingDTO parking);

    public void delete(int id);

    public ParkingEntity create(ParkingDTO parking);
}
