package com.ecole221.service.service.controller;


import com.ecole221.common.service.dto.ServiceDTO;
import com.ecole221.service.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService service;

    @GetMapping
    public ResponseEntity getAllServices() {
        return new ResponseEntity(service.getAllServices(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity saveService(@RequestBody ServiceDTO serviceDTO) {
        return new ResponseEntity(service.saveService(serviceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceDTO changeStatus(@PathVariable UUID id){
        return service.changeStatus(id);
    }
}
