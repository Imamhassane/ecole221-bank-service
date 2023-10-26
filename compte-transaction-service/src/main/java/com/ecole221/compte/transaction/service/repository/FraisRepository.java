package com.ecole221.compte.transaction.service.repository;

import com.ecole221.compte.transaction.service.model.Frais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FraisRepository extends JpaRepository<Frais, UUID> {
}
