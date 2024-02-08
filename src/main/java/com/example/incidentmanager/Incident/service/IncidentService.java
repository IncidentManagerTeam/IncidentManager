package com.example.incidentmanager.Incident.service;

import java.util.List;
import com.example.incidentmanager.Incident.domain.IncidentEntity;
import com.example.incidentmanager.User.domain.UserEntity;

public interface IncidentService {

    public List<IncidentEntity> getAll();
    
    public IncidentEntity getOne(int id);
    
    public IncidentEntity create(byte[] image, String description, String state, UserEntity user);

    public IncidentEntity update(int id, byte[] image, String description, String state, UserEntity user);

    public void delete(int id);

}
