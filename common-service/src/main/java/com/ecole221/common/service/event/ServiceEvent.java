package com.ecole221.common.service.event;

import com.ecole221.common.service.dto.ServiceDTO;

import java.util.Date;
import java.util.UUID;

public class ServiceEvent implements Event{

    private ServiceDTO serviceDTO;
    private ServiceStatus serviceStatus;
    private PaiementEvent paiementEvent;
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();


    public ServiceEvent(ServiceDTO serviceDTO, ServiceStatus serviceStatus, UUID eventId, Date eventDate) {
        this.serviceDTO = serviceDTO;
        this.serviceStatus = serviceStatus;
        this.eventId = eventId;
        this.eventDate = eventDate;
    }



    public ServiceEvent(ServiceDTO serviceDTO, PaiementEvent event) {
        this.serviceDTO = serviceDTO;
        this.paiementEvent = event;
    }

    public ServiceEvent(){}

    @Override
    public UUID getEventId() {
        return eventId;
    }
    @Override
    public Date getEventDate() {return eventDate;}

    public ServiceDTO getServiceDTO() {
        return serviceDTO;
    }

    public void setServiceDTO(ServiceDTO serviceDTO) {
        this.serviceDTO = serviceDTO;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
    public PaiementEvent getPaiementEvent() {
        return paiementEvent;
    }

    public void setPaiementEvent(PaiementEvent paiementEvent) {
        this.paiementEvent = paiementEvent;
    }
}
