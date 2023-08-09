package com.ecole221.service.service.mapper;

import com.ecole221.common.service.dto.ServiceDTO;
import com.ecole221.common.service.event.ServiceStatus;
import com.ecole221.service.service.model.ServiceModel;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public ServiceDTO serviceToServiceDTO(ServiceModel serviceModel) {
        return ServiceDTO.builder()
                .id(serviceModel.getId())
                .libelle(serviceModel.getLibelle())
                .prix(serviceModel.getPrix())
                .serviceStatus(serviceModel.getServiceStatus().toString())
                .build();
    }


    public ServiceModel serviceDTOToService(ServiceDTO serviceDTO) {
        return ServiceModel.builder()
                .id(serviceDTO.getId())
                .libelle(serviceDTO.getLibelle())
                .prix(serviceDTO.getPrix())
                .serviceStatus(ServiceStatus.valueOf(serviceDTO.getServiceStatus()))
                .build();
    }
}
