package com.ecole221.common.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementDTO {
    private UUID id;
    private String service;
    private String clientTel;
    private String status;
    private BigDecimal montant;
    private String info;
}