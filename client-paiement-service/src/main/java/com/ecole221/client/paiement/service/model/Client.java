package com.ecole221.client.paiement.service.model;


import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.event.ClientStatus;
import com.ecole221.common.service.event.CompteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Client {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    private String nomComplet;
    private String tel;
    private ClientStatus clientStatus;
    private CompteStatus compteStatus;
}
