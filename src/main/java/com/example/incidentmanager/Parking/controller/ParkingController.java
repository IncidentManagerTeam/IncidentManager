package com.example.incidentmanager.Parking.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.incidentmanager.Parking.domain.ParkingEntity;
import com.example.incidentmanager.Parking.service.ParkingService;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ParkingController {
    private ParkingService parkingSvc;

    public ParkingController(ParkingService service) {
        this.parkingSvc = service;
    }

    @GetMapping("api/parkings")
    public Iterable<ParkingEntity> getAll() {
        return this.parkingSvc.getAll();
    }

    @GetMapping("api/parking/{id}")
    public ParkingEntity getOne(@PathVariable int id, @RequestBody ParkingEntity parking) {
        return this.parkingSvc.getOne(id);
    }

    @PostMapping("api/parkings")
    public ParkingEntity createOne(@RequestBody ParkingEntity parking) {
        try {
            return this.parkingSvc.create(parking);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Ya existe una solicitud para este usuario");
        }

    }

    @DeleteMapping("api/parkings/{id}")
    public void deleteOne(@PathVariable int id, @RequestBody ParkingEntity parking) {
        this.parkingSvc.delete(id);
    }

    @PutMapping("api/parkings/{id}")
    public ParkingEntity updateParking(@PathVariable int id, @RequestBody ParkingEntity parking) {
        try {
            return this.parkingSvc.update(id, parking.getLicensePlate(), parking.getCompanion(), parking.getState(),
                    parking.getDate());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No eexiste el usuario con el id");
        }

    }
}
