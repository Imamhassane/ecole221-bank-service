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
public class ServiceDTO {
    private UUID id;
    private String libelle;
    private BigDecimal prix;
    private String serviceStatus;
    //private UUID paiement;
}
