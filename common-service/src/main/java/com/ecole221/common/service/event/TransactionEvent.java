package com.ecole221.common.service.event;

import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.dto.CompteDTO;
import com.ecole221.common.service.dto.PaiementDTO;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TransactionEvent implements Event{
    private PaiementDTO paiementDTO;
    private ClientDTO clientDTO;
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();

    public TransactionEvent(PaiementDTO paiementDTO, ClientDTO clientDTO) {
        this.paiementDTO = paiementDTO;
        this.clientDTO = clientDTO;
    }

    public TransactionEvent() {
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
