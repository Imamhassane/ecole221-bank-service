package com.ecole221.compte.transaction.service.mapper;

import com.ecole221.common.service.dto.CompteDTO;
import com.ecole221.common.service.dto.TransactionDTO;
import com.ecole221.compte.transaction.service.model.Compte;
import com.ecole221.compte.transaction.service.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class CompteTransactionMapper {

    public CompteDTO compteToCompteDTO(Compte compte){
        return CompteDTO.builder()
                .clientId(compte.getClient_id())
                .solde(compte.getSolde())
                .build();
    }
    public Compte compteDTOToCompte(CompteDTO compte){
        return Compte.builder()
                .client_id(compte.getClientId())
                .solde(compte.getSolde())
                .build();
    }
    public TransactionDTO transactionToTransactionDTO(Transaction transaction){
        return TransactionDTO.builder()
                .id(transaction.getId())
                .client_nom(transaction.getClient_nom())
                .client_tel(transaction.getTel())
                .date(transaction.getDate())
                .montant(transaction.getMontant())
                .service(transaction.getService())
                .build();
    }
    /*public Transaction transactionDTOToTransaction (TransactionDTO transaction){
        return Transaction.builder()
                .id(transaction.getId())
                .client_nom(transaction.getClient_nom())
                .tel(transaction.getClient_tel())
                .date(transaction.getDate())
                .montant(transaction.getMontant())
                .service(transaction.getService())
                .build();

    }*/

}
