package com.ecole221.service.service.service;

import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.dto.ServiceDTO;
import com.ecole221.common.service.event.*;
import com.ecole221.service.service.exceptions.ServiceException;
import com.ecole221.service.service.exceptions.ServiceNotFoundException;
import com.ecole221.service.service.mapper.ServiceMapper;
import com.ecole221.service.service.model.ServiceModel;
import com.ecole221.service.service.repository.ServiceRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.Uuid;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper mapper;
    private final KafkaTemplate<String, ServiceEvent> kafkaTemplate;

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(mapper::serviceToServiceDTO)
                .collect(Collectors.toList());
    }
    public void serviceExist(String lib){
        boolean isExist = getAllServices().stream()
                .noneMatch(service -> Objects.equals(service.getLibelle(), lib));
        if (!isExist) throw new ServiceException("Ce service existe!");
    }
    public ServiceModel saveService(ServiceDTO serviceDTO) {
        serviceExist(serviceDTO.getLibelle());
        Utils.prixIsValid(serviceDTO.getPrix());
        serviceDTO.setServiceStatus("ACTIF");
        return serviceRepository.save(mapper.serviceDTOToService(serviceDTO));
    }


    @Transactional
    public ServiceEvent serviceIsActif(PaiementEvent event) {
        ServiceEvent serviceEvent = null;
        Optional<ServiceModel> service = serviceRepository.findServiceByLibelle(event.getPaiementDTO().getService());
        if (service.isPresent()) {
            serviceEvent = new ServiceEvent(mapper.serviceToServiceDTO(service.get()),event);
        }
        return serviceEvent;
    }
    
    public ServiceDTO changeStatus(ServiceDTO serviceDTO){
        ServiceModel serviceModel = serviceRepository.findServiceByLibelle(serviceDTO.getLibelle())
                .orElseThrow(()-> new ServiceNotFoundException("Service not found!"));
        Objects.requireNonNull(serviceModel).
                setServiceStatus(serviceModel.getServiceStatus().equals(ServiceStatus.ACTIF)
                ? ServiceStatus.INACTIF : ServiceStatus.ACTIF);
        serviceRepository.save(serviceModel);
        return mapper.serviceToServiceDTO(serviceModel);
    }
}
