package com.ecole221.client.paiement.service.controller;

import com.ecole221.client.paiement.service.model.Client;
import com.ecole221.client.paiement.service.service.ClientService;
import com.ecole221.common.service.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity getAllClients() {
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity(clientService.saveClient(clientDTO), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllClients() {
        clientService.getClientRepository().deleteAll();
    }
}
