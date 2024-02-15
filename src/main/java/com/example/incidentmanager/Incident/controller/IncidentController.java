package com.example.incidentmanager.Incident.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.incidentmanager.Incident.service.IncidentService;
import com.example.incidentmanager.Incident.domain.IncidentEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class IncidentController {
    private IncidentService incidentSvc;

    public IncidentController(IncidentService service) {
        this.incidentSvc = service;
    }

    @GetMapping("api/incidents")
    public Iterable<IncidentEntity> getAll() {
        return this.incidentSvc.getAll();
    }

    @GetMapping("api/incident/{id}")
    public IncidentEntity getOne(@PathVariable int id) {
        return this.incidentSvc.getOne(id);
    }

    @PostMapping("api/incident")
    public IncidentEntity createOne(@RequestBody IncidentEntity incident) {
        return this.incidentSvc.create(incident.getId(), incident.getImage(), incident.getDescription(),
                incident.getState(), incident.getUser());
    }

    @PutMapping("api/incident")
    public IncidentEntity updateOne(@RequestBody IncidentEntity incident) {
        return this.incidentSvc.update(incident.getId(), incident.getImage(), incident.getDescription(),
                incident.getState(), incident.getUser());
    }

    @DeleteMapping("api/incident/{id}")
    public void deleteOne(@PathVariable int id) {
        this.incidentSvc.delete(id);
    }
}
