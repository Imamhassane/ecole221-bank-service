package com.ecole221.client.paiement.service.model;

import com.ecole221.common.service.event.ClientStatus;
import com.ecole221.common.service.event.PaiementStatus;
import jakarta.persistence.*;
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
public class Paiement {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    private BigDecimal montant;
    private String service;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    private PaiementStatus paiementStatus;
    private String info;
}
