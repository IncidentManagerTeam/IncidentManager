package com.example.incidentmanager.Parking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.incidentmanager.Parking.domain.ParkingEntity;
import com.example.incidentmanager.Parking.service.ParkingService;

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

    @GetMapping("/parkings")
    public Iterable<ParkingEntity> getAll() {
        return this.parkingSvc.getAll();
    }

    @GetMapping("/parking/{id}")
    public ParkingEntity getOne(@PathVariable int id, @RequestBody ParkingEntity parking) {
        return this.parkingSvc.getOne(id);
    }

    @PostMapping("/parkings/{id}")
    public ParkingEntity createOne(@PathVariable int id, @RequestBody ParkingEntity parking) {
        return this.parkingSvc.create(parking.getUser(), parking.getLicensePlate(), parking.getCompanion(),
                parking.getState(), parking.getDate());
    }

    @DeleteMapping("/parkings/{id}")
    public void deleteOne(@PathVariable int id, @RequestBody ParkingEntity parking) {
        this.parkingSvc.delete(id);
    }

    @PutMapping("parkings/{id}")
    public ParkingEntity updateParking(@PathVariable int id, @RequestBody ParkingEntity parking) {
        return this.parkingSvc.update(id, parking.getLicensePlate(), parking.getCompanion(), parking.getState(),
                parking.getDate());
    }
}
