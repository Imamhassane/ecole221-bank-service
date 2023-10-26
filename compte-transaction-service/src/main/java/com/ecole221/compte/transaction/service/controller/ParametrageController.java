package com.ecole221.compte.transaction.service.controller;

import com.ecole221.compte.transaction.service.service.CompteService;
import com.ecole221.compte.transaction.service.service.ParametrageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/pourcentage")
@RequiredArgsConstructor
public class ParametrageController {
    private final ParametrageService parametrageService;

    @GetMapping
    @ResponseBody
    public ResponseEntity save(){
        return new ResponseEntity(parametrageService.save(), HttpStatus.OK);
    }


}
