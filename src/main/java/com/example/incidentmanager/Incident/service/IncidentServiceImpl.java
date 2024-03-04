package com.example.incidentmanager.Incident.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidentmanager.Incident.domain.IncidentEntity;
import com.example.incidentmanager.User.domain.UserEntity;

@Service
public class IncidentServiceImpl implements IncidentService {
    private List<IncidentEntity> _incidentRequest = new ArrayList<IncidentEntity>();
    
    @Override
    public List<IncidentEntity> getAll() {
        return _incidentRequest;
    }

    @Override
    public IncidentEntity getOne(int id) {
        for (IncidentEntity incident : _incidentRequest) {
            if (incident.getId() == id) {
                return incident; // Retorna el incidente si se encuentra en la lista
            }
        }
        System.out.println("No se encontró ningún incidente con el ID proporcionado: " + id);
        // Retorna null si no se encuentra el incidente
        return null;
    }

    @Override
    public IncidentEntity create(int id, byte[] image, String description, String state, UserEntity user) {
        IncidentEntity incident = new IncidentEntity(id, image, description, state, user);
        return incident;
    }


    @Override
    public IncidentEntity update(int id, byte[] image, String description, String state, UserEntity user) {
        boolean exist = requestExists(id);
        if (exist) {
            for (IncidentEntity incident : _incidentRequest) {
                if (incident.getId() == id) {
                    incident.setImage(image);
                    incident.setDescription(description);
                    incident.setState(state);
                    // Retorna el incidente modificado si se encontró y actualizó correctamente
                    return incident;
                }
            }
        }
        System.out.println("No se encontró ningún incidente con el ID proporcionado: " + id);
        // Retorna null si no se encuentra el incidente a modificar
        return null;
    }

    @Override
    public void delete(int id) {
        boolean exist = requestExists(id);
        if (exist) {
            Iterator<IncidentEntity> iterator = _incidentRequest.iterator();
            while (iterator.hasNext()) {
                IncidentEntity incident = iterator.next();
                if (incident.getId() == id) {
                    iterator.remove();
                    return; // Termina el método después de eliminar el incidente
                }
            }
        } else {
            System.out.println("No existe el incidente con ID: " + id);
        }
    }

    // Comprueba si el incidente existe en la lista
    private boolean requestExists(int id) {
        for (IncidentEntity incident : _incidentRequest) {
            if (incident.getId() == id) {
                return true; // Si encuentra un incidente con el ID proporcionado, retorna true
            }
        }
        System.out.println("No existe el incidente con ID: " + id);
        return false; // Si no encuentra ningún incidente con el ID proporcionado, retorna false
    }
}
