package com.example.incidentmanager.Parking.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface ParkingRepository extends CrudRepository<ParkingEntity,Integer>{
        public Optional<ParkingEntity> findByEmailIgnoreCase(String email);
        public boolean existsByEmailIgnoreCase(String email);
}
