package com.ecole221.service.service.repository;

import com.ecole221.service.service.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, UUID> {
    Optional<ServiceModel> findServiceByLibelle(String libelle);
}
