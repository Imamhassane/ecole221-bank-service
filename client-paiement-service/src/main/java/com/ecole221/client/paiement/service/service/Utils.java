package com.ecole221.client.paiement.service.service;

import com.ecole221.client.paiement.service.exception.client.ClientException;
import com.ecole221.common.service.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class Utils {

    public boolean checkTelSyntaxe(String tel) {
        String regex = "^\\+2217[05678]\\d{7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tel);
        return  !matcher.matches();
    }

    public void checkData(ClientDTO clientDTO){
        if (checkTelSyntaxe(clientDTO.getTel()))throw new ClientException("Numéro invalide!");
        if (clientDTO.getSolde().compareTo(BigDecimal.ZERO) <= 0)
            throw new ClientException("Solde invalide!");
    }

}
