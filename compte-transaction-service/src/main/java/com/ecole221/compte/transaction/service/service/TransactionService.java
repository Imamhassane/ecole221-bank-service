package com.ecole221.compte.transaction.service.service;

import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.dto.PaiementDTO;
import com.ecole221.common.service.dto.TransactionDTO;
import com.ecole221.common.service.event.PaiementEvent;
import com.ecole221.common.service.event.PaiementStatus;
import com.ecole221.compte.transaction.service.mapper.CompteTransactionMapper;
import com.ecole221.compte.transaction.service.model.Transaction;
import com.ecole221.compte.transaction.service.repository.TransactionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class TransactionService {

    private final TransactionRepository repository;
    private final CompteTransactionMapper mapper;


    public void saveTransaction(PaiementEvent paiementEvent){
        if (paiementEvent.getPaiementStatus().equals(PaiementStatus.COMPLETE)){
            Transaction transaction = new Transaction();
            PaiementDTO paiementDTO = paiementEvent.getPaiementDTO();

            transaction.setService(paiementDTO.getService());
            transaction.setMontant(paiementDTO.getMontant());
            transaction.setDate(new Date());
            transaction.setClient_nom(paiementEvent.getClientDTO().getNomComplet());
            transaction.setTel(paiementEvent.getClientDTO().getTel());

            repository.save(transaction);
        }
    }
    public List<TransactionDTO> getAllTransactions() {
        return repository.findAll().stream()
                .map(mapper::transactionToTransactionDTO)
                .toList();
    }

    public List<TransactionDTO> getTransactionByClient(String tel){
        return repository.findAllByTel(tel).stream()
                .map(mapper::transactionToTransactionDTO)
                .toList();
    }
}
