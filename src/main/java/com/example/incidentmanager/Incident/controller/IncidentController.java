package com.example.incidentmanager.Incident.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotAcceptable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.incidentmanager.Incident.service.IncidentService;
import com.example.incidentmanager.Incident.domain.IncidentDTO;
import com.example.incidentmanager.Incident.domain.IncidentEntity;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class IncidentController {
    private IncidentService incidentSvc;

    public IncidentController(IncidentService service) {
        this.incidentSvc = service;
    }

    @GetMapping("api/incident")
    public Iterable<IncidentEntity> getAll() {
        return this.incidentSvc.getAll();
    }

    @GetMapping("api/incident/{id}")
    public IncidentEntity getOne(@PathVariable int id) {
        return this.incidentSvc.getOne(id);
    }

    @PostMapping(path = "api/incident", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IncidentEntity createOne(@RequestPart("incident") IncidentDTO incident,
            @RequestPart MultipartFile image) {
        try {
            InputStream imageInputStream = image.getInputStream();
            byte[] imageBytes = imageInputStream.readAllBytes();
            return this.incidentSvc.create(incident, imageBytes);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No envió un formao correcto de imágen");
        }
    }

    @PutMapping("api/incident/{id}")
    public IncidentEntity updateOne(@PathVariable int id,
            @RequestPart("incident") IncidentDTO incident,
            @RequestParam("image") MultipartFile image) {
        try {
            InputStream imageInputStream = image.getInputStream();
            byte[] imageBytes = imageInputStream.readAllBytes();
            return this.incidentSvc.update(id, incident, imageBytes);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No envió un formao correcto de imágen");
        }

    }

    @DeleteMapping("api/incident/{id}")
    public void deleteOne(@PathVariable int id) {
        this.incidentSvc.delete(id);
    }
}
