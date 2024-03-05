package com.example.incidentmanager.Incident.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.incidentmanager.Incident.domain.IncidentDTO;
import com.example.incidentmanager.Incident.domain.IncidentEntity;

public interface IncidentService {

    public Iterable<IncidentEntity> getAll();

    public IncidentEntity getOne(int id);

    public IncidentEntity create(IncidentDTO incident, byte[] image);

    public IncidentEntity update(int id, IncidentDTO incident, byte[] image);

    public void delete(int id);

}
