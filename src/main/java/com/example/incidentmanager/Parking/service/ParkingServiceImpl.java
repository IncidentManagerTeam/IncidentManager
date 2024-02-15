package com.example.incidentmanager.Parking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidentmanager.Parking.domain.ParkingEntity;
import com.example.incidentmanager.User.domain.UserEntity;

@Service
public class ParkingServiceImpl implements ParkingService {
    private List<ParkingEntity> _parkingRequests = new ArrayList<ParkingEntity>();

    @Override
    public List<ParkingEntity> getAll() {
        return _parkingRequests;
    }

    @Override
    public ParkingEntity getOne(int id) {
        boolean exist = requestExists(id);
        if (exist) {
            for (ParkingEntity parking : _parkingRequests) {
                if (parking.getId() == id) {
                    return parking;
                }
            }
        }
        System.out.println("Ha ocurrido un error con la obtencion de la solicitud");
        return new ParkingEntity(null, id, null, null, new UserEntity(-1, null, null, null, null, null, null));
    }

    @Override
    public ParkingEntity update(int id, String licensePlate, int companion, String state, Date date) {
        boolean exist = requestExists(id);
        if (exist) {
            for (ParkingEntity parking : _parkingRequests) {
                if (parking.getId() == id) {
                    parking.setCompanion(companion);
                    parking.setDate(date);
                    parking.setLicensePlate(licensePlate);
                    parking.setState(state);
                }
            }
        }
        System.out.println("Ha ocurrido un error con la obtencion de la solicitud");
        return new ParkingEntity(null, id, null, null, new UserEntity(-1, null, null, null, null, null, null));
    }

    @Override
    public void delete(int id) {
        boolean exist = requestExists(id);
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
    public ParkingEntity create(UserEntity user, String licensePlate, int companion, String state, Date date) {
        for (ParkingEntity parking : _parkingRequests) {
            if (parking.getCompanion() == companion) {
                System.out.println("Error, ya existe una solicitud de parking para el usuario " + user);
                return new ParkingEntity(null, -1, null, null, user);
            }
        }
        ParkingEntity parking = new ParkingEntity(licensePlate, companion, state, date, user);
        return parking;
    }

    // Comprueba de que la solicitud existe
    private boolean requestExists(int id) {
        for (ParkingEntity parking : _parkingRequests) {
            if (parking.getId() == id) {
                return true;
            } else {
                System.out.println("No existe solicitud de parking");
                return false;
            }
        }
        return false;
    }

}
