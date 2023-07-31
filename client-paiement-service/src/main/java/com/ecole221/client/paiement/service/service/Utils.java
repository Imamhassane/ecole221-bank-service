package com.ecole221.client.paiement.service.service;

import com.ecole221.client.paiement.service.exception.client.ClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class Utils {
    private static final BigDecimal MAX_VALUE = BigDecimal.valueOf(5000);

    public boolean checkTelSyntaxe(String tel) {
        String regex = "^\\+2217[05678]\\d{7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tel);
        return  !matcher.matches();
    }

    public void checkData(String tel){
        if (checkTelSyntaxe(tel))throw new ClientException("Numéro invalide!");
    }

}
