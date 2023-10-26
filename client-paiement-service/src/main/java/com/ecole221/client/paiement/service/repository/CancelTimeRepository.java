package com.ecole221.client.paiement.service.repository;


import com.ecole221.client.paiement.service.model.CancelTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CancelTimeRepository extends JpaRepository<CancelTime, UUID> {

    Optional<CancelTime> findByEtat(boolean etat);
}
