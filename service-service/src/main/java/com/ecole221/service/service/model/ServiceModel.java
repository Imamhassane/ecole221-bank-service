package com.ecole221.service.service.model;

import com.ecole221.common.service.event.ServiceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class ServiceModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    private String libelle;
    private BigDecimal prix;
    private ServiceStatus serviceStatus;

}
