package com.example.incidentmanager.Parking.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.incidentmanager.Parking.core.OneParkingEntityAlreadyExistForUser;
import com.example.incidentmanager.Parking.domain.ParkingDTO;
import com.example.incidentmanager.Parking.domain.ParkingEntity;
import com.example.incidentmanager.Parking.domain.ParkingRepository;
import com.example.incidentmanager.User.service.UserService;

@Service
public class ParkingServiceImpl implements ParkingService {
    private ParkingRepository repository;
    private UserService userSvc;

    ParkingServiceImpl(ParkingRepository repository, UserService userSvc) {
        this.repository = repository;
        this.userSvc = userSvc;

    }

    @Override
    public Iterable<ParkingEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public ParkingEntity getOne(int id) {
        boolean exist = requestExistsById(id);
        if (exist) {
            for (ParkingEntity parking : repository.findAll()) {
                if (parking.getId() == id) {
                    return parking;
                }
            }
        }
        System.out.println("Ha ocurrido un error con la obtencion de la solicitud");
        return new ParkingEntity(null, id, null, null, null);
    }

    @Override
    public ParkingEntity update(int id, ParkingDTO parking) {
        boolean exists = requestExistsById(id);
        if (exists) {
            for (ParkingEntity parkings : repository.findAll()) {
                if (parkings.getId() == id) {
                    parkings.setCompanion(parking.getCompanion());
                    parkings.setDate(parking.getDate());
                    parkings.setLicensePlate(parking.getLicensePlate());
                    parkings.setState(parking.getState());
                    repository.save(parkings);
                    return parkings;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        boolean exists = requestExistsById(id);
        if (exists) {
            for (ParkingEntity parkings : repository.findAll()) {
                if (parkings.getId() == id) {
                    repository.delete(parkings);
                }
            }
        }
    }

    @Override
    public ParkingEntity create(ParkingDTO parking) {
        var validator = this.userSvc.getOne(parking.getUser_id());
        if (validator != null) {
            ParkingEntity parkingEntity = new ParkingEntity(parking.getLicensePlate(), parking.getCompanion(),
                    parking.getState(), parking.getDate(), validator);
            var exists = requestExistsById(parkingEntity.getId());
            if (!exists)
                return repository.save(parkingEntity);
            else
                throw new OneParkingEntityAlreadyExistForUser();
        } else
            throw new UsernameNotFoundException("No se ha encontrado un usuario correspondiente");
    }

    // Comprueba de que la solicitud existe
    /*private boolean requestExists(ParkingEntity parking) {
        if (repository.findAll() != null) {
            for (ParkingEntity _parking : repository.findAll()) {
                if (parking == _parking) {
                    return true;
                }
            }
            return false;
        } else
            return false;
    }*/

    private boolean requestExistsById(int id) {
        if (repository.findAll() != null) {
            for (ParkingEntity _parking : repository.findAll()) {
                if (_parking.getId() == id) {
                    return true;
                }
            }
            return false;
        } else
            return false;
    }
}
