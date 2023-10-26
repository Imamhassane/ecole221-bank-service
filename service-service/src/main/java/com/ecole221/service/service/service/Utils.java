package com.ecole221.service.service.service;

import com.ecole221.service.service.exceptions.ServiceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Utils {

    public static void prixIsValid(BigDecimal prix) {

        if (prix.compareTo(BigDecimal.ZERO) <= 0)
            throw new ServiceException("Prix invalide!");
    }
}
