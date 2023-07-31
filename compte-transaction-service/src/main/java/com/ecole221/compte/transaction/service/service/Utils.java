package com.ecole221.compte.transaction.service.service;

import com.ecole221.compte.transaction.service.exception.compte.CompteException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Utils {

    public void checkSoldeIsValid(BigDecimal solde) {
        BigDecimal minimumBalance = new BigDecimal("5000");
        int comparisonResult = solde.compareTo(minimumBalance);
        if (comparisonResult < 0) {
            throw new CompteException("Solde invalide. Minimum 5000.");
        }
    }
}
