package com.example.incidentmanager.Parking.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.incidentmanager.User.domain.UserEntity;




public interface ParkingRepository extends CrudRepository<ParkingEntity,Integer>{
        public Optional<ParkingEntity>findByUser(UserEntity user);
}
