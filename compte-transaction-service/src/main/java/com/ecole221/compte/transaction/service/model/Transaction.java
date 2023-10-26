package com.ecole221.compte.transaction.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String client_nom;
    private String tel;
    private String service;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private BigDecimal montant;
    private BigDecimal frais;
    private double pourcentage;
}
