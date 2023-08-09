package com.ecole221.client.paiement.service.controller;

import com.ecole221.client.paiement.service.service.PaiementService;
import com.ecole221.common.service.dto.PaiementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final PaiementService paiementService;

    @GetMapping
    public List<PaiementDTO> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    @PostMapping
    public ResponseEntity savePaiement(@RequestBody PaiementDTO paiementDTO) {
        return new ResponseEntity(paiementService.savePaiement(paiementDTO), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(){
        paiementService.getPaiementRepository().deleteAll();
    }
}
