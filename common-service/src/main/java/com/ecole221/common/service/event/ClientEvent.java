package com.ecole221.common.service.event;

import com.ecole221.common.service.dto.ClientDTO;

import java.util.Date;
import java.util.UUID;


public class ClientEvent implements  Event {

    private ClientDTO clientDTO;
    private ClientStatus clientStatus;
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();

    public ClientEvent(ClientDTO clientDTO, ClientStatus clientStatus, UUID eventId, Date eventDate) {
        this.clientDTO = clientDTO;
        this.clientStatus = clientStatus;
        this.eventId = eventId;
        this.eventDate = eventDate;
    }
    public ClientEvent(ClientDTO clientDTO, ClientStatus clientStatus) {
        this.clientDTO = clientDTO;
        this.clientStatus = clientStatus;
    }
    public ClientEvent() {
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }
    @Override
    public Date getEventDate() {return eventDate;}
}
