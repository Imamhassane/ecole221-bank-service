package com.ecole221.common.service.event;

import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.dto.PaiementDTO;

import java.util.Date;
import java.util.UUID;

public class PaiementEvent implements  Event {
    private PaiementDTO paiementDTO;
    private PaiementStatus paiementStatus;
    private ClientDTO clientDTO;
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();

    public PaiementEvent(PaiementDTO paiementDTO, PaiementStatus paiementStatus) {
        this.paiementDTO = paiementDTO;
        this.paiementStatus = paiementStatus;
    }

    public PaiementEvent(PaiementDTO paiementDTO, PaiementStatus paiementStatus,ClientDTO clientDTO) {
        this.paiementDTO = paiementDTO;
        this.clientDTO = clientDTO;
        this.paiementStatus = paiementStatus;
    }

    public PaiementEvent(PaiementDTO paiementDTO, PaiementStatus paiementStatus, UUID eventId, Date eventDate) {
        this.paiementDTO = paiementDTO;
        this.paiementStatus = paiementStatus;
        this.eventId = eventId;
        this.eventDate = eventDate;
    }

    public PaiementEvent(){}

    public PaiementDTO getPaiementDTO() {
        return paiementDTO;
    }

    public void setPaiementDTO(PaiementDTO paiementDTO) {
        this.paiementDTO = paiementDTO;
    }

    public PaiementStatus getPaiementStatus() {
        return paiementStatus;
    }

    public void setPaiementStatus(PaiementStatus paiementStatus) {
        this.paiementStatus = paiementStatus;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getEventDate() {
        return eventDate;
    }
}
