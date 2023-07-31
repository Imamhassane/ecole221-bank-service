package com.ecole221.compte.transaction.service.controller;

import com.ecole221.compte.transaction.service.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {
    private final CompteService compteService;

    @GetMapping
    @ResponseBody
    public ResponseEntity findAll(){
        return new ResponseEntity(compteService.getAllComptes(), HttpStatus.OK);
    }

    @GetMapping("/{client}")
    @ResponseBody
    public ResponseEntity find(@PathVariable UUID client){
        return new ResponseEntity(compteService.findByClient(client), HttpStatus.OK);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        compteService.getCompteRepository().deleteAll();
    }
}
