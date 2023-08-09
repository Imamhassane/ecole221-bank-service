package com.ecole221.compte.transaction.service.controller;

import com.ecole221.compte.transaction.service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity findAll(){
        return new ResponseEntity(service.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{tel}")
    @ResponseBody
    public ResponseEntity find(@PathVariable String tel){
        return new ResponseEntity(service.getTransactionByClient(tel), HttpStatus.OK);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        service.getRepository().deleteAll();
    }
}
