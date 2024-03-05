package com.example.incidentmanager.Incident.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.incidentmanager.Incident.domain.IncidentDTO;
import com.example.incidentmanager.Incident.domain.IncidentEntity;
import com.example.incidentmanager.Incident.domain.IncidentRepository;
import com.example.incidentmanager.User.service.UserService;

@Service
public class IncidentServiceImpl implements IncidentService {

    private IncidentRepository repository;
    private UserService userSvc;

    IncidentServiceImpl(IncidentRepository repository,UserService userSvc) {
        this.repository = repository;
        this.userSvc = userSvc;
    }

    @Override
    public Iterable<IncidentEntity> getAll() {
        var result = repository.findAll();
        return result;
    }

    @Override
    public IncidentEntity getOne(int id) {
        var validator = requestExists(id);
        if (validator) {
            return repository.findById(id).get();
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado ningun incidente con id: " + id);
    }

    @Override
    public IncidentEntity create(IncidentDTO incident, byte[] image) {
        var validator = this.userSvc.getOne(incident.getUserId());
        String imagen = "";
        if (validator != null) {
            try {
                IncidentEntity incidentEntity = new IncidentEntity(imagen,incident.getDescription(), incident.getState(), validator, incident.getClassroom(),incident.getTitle());
                return repository.save(incidentEntity);
            }catch(Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Ya existe una solicitud con al misma id");
            }
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado ningun usuario asociado ");
    }

    @Override
    public IncidentEntity update(int id, IncidentDTO incident, byte[] image) {
        boolean exist = requestExists(id);
        if (exist) {
            IncidentEntity _incident = repository.findById(id).get();
            _incident.setImage("");
            _incident.setDescription(incident.getDescription());
            _incident.setState(incident.getState());
            _incident.setClassroom(incident.getClassroom());
            _incident.setTitle(incident.getTitle());
            repository.save(_incident);
            return _incident;
        }
        System.out.println("No se encontró ningún incidente con el ID proporcionado: " + id);
        // Retorna null si no se encuentra el incidente a modificar
        return null;
    }

    @Override
    public void delete(int id) {
        boolean exist = requestExists(id);
        if (exist) {
            repository.delete(repository.findById(id).get());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No existe ningun incidente con el id proporcionado");
        }
    }

    // Comprueba si el incidente existe en la lista
    private boolean requestExists(int id) {
        var validator = repository.findAll();
        if (validator != null) {
            for (IncidentEntity incident : repository.findAll()) {
                if (incident.getId() == id) {
                    return true; // Si encuentra un incidente con el ID proporcionado, retorna true
                }
            }
            return false; // Si no encuentra ningún incidente con el ID proporcionado, retorna false
        } else
            return false;
    }
}
