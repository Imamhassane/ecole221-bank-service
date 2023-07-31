package com.ecole221.common.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize(using = ClientDTOSerializer.class)
public class ClientDTO {
    private UUID id;
    private String nomComplet;
    private String tel;
    //private List<PaiementDTO> paiements;
    private String clientStatus;
    private String compteStatus;
    private BigDecimal solde;

}