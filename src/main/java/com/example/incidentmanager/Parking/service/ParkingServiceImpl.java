package com.example.incidentmanager.Parking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.incidentmanager.Parking.core.OneParkingEntityAlreadyExistForUser;
import com.example.incidentmanager.Parking.domain.ParkingDTO;
import com.example.incidentmanager.Parking.domain.ParkingEntity;
import com.example.incidentmanager.Parking.domain.ParkingRepository;
import com.example.incidentmanager.User.domain.UserEntity;
import com.example.incidentmanager.User.service.UserService;

@Service
public class ParkingServiceImpl implements ParkingService {
    private List<ParkingEntity> _parkingRequests = new ArrayList<ParkingEntity>();
    private ParkingRepository repository;
    private UserService userSvc;

    ParkingServiceImpl(ParkingRepository repository, UserService userSvc) {
        this.repository = repository;
        this.userSvc = userSvc;
    }

    @Override
    public Iterable<ParkingEntity> getAll() {
        if (repository.findAll() != null)
            return repository.findAll();
        return null;
    }

    @Override
    public ParkingEntity getOne(int id) {
        boolean exist = requestExistsById(id);
        if (exist) {
            for (ParkingEntity parking : _parkingRequests) {
                if (parking.getId() == id) {
                    return parking;
                }
            }
        }
        System.out.println("Ha ocurrido un error con la obtencion de la solicitud");
        return new ParkingEntity(null, id, null, null,null);
    }

    @Override
    public ParkingEntity update(int id, String licensePlate, int companion, String state, Date date) {
        boolean exist = requestExistsById(id);
        if (exist) {
            for (ParkingEntity parking : _parkingRequests) {
                if (parking.getId() == id) {
                    parking.setCompanion(companion);
                    parking.setDate(date);
                    parking.setLicensePlate(licensePlate);
                    parking.setState(state);
                    return repository.save(parking);
                }
            }
        }
        System.out.println("Ha ocurrido un error con la obtencion de la solicitud");
        return new ParkingEntity(null, id, null, null,null);
    }

    @Override
    public void delete(int id) {
        boolean exist = requestExistsById(id);
        if (exist) {
            for (ParkingEntity parking : _parkingRequests) {
                if (parking.getId() == id) {
                    _parkingRequests.remove(parking);
                }
            }
        } else
            System.out.println("No existe solicitud de parking");
    }

    @Override
    public ParkingEntity create(ParkingDTO parking) {
        var validator = this.userSvc.getOne(parking.getUser_id());
        if(validator != null){
            ParkingEntity parkingEntity = new ParkingEntity(parking.getLicensePlate(),parking.getCompanion(),parking.getState(),parking.getDate(),validator);
            var exists = requestExists(parkingEntity);
            if(!exists)
                return repository.save(parkingEntity);
            else
                throw new OneParkingEntityAlreadyExistForUser();
        }
        else
            throw new UsernameNotFoundException("No se ha encontrado un usuario correspondiente");
    }

    // Comprueba de que la solicitud existe
    private boolean requestExists(ParkingEntity parking) {
        for (ParkingEntity _parking : _parkingRequests) {
            if (parking == _parking) {
                return true;
            } else {
                System.out.println("No existe solicitud de parking");
                return false;
            }
        }
        return false;
    }

    private boolean requestExistsById(int id) {
        for (ParkingEntity _parking : _parkingRequests) {
            if (_parking.getId() == id) {
                return true;
            } else {
                System.out.println("No existe solicitud de parking");
                return false;
            }
        }
        return false;
    }
}
