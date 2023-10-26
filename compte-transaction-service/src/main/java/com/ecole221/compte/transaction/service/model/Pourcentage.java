package com.ecole221.compte.transaction.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pourcentage {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    private double pourcentage;
    private boolean etat;
}
