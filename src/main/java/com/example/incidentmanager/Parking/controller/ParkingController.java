package com.example.incidentmanager.Parking.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.incidentmanager.Parking.domain.ParkingDTO;
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

    @GetMapping("api/parkings/{id}")
    public ParkingEntity getOne(@PathVariable(name = "id") int id) {
        return this.parkingSvc.getOne(id);
    }

    @PostMapping("api/parkings")
    public ParkingEntity createOne(@RequestBody ParkingDTO parking) {
        try {
            var parkings = this.parkingSvc.create(parking);
            return parkings;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Ya existe una solicitud para este usuario");       
        }

    }

    @DeleteMapping("api/parkings/{id}")
    public void deleteOne(@PathVariable int id) {
        this.parkingSvc.delete(id);
    }

    @PutMapping("api/parkings/{id}")
    public ParkingEntity updateParking(@PathVariable int id, @RequestBody ParkingDTO parking) {
        try {
            return this.parkingSvc.update(id,parking);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario con el id");
        }

    }
}
