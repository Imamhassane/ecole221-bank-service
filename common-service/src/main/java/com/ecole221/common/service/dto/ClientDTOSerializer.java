package com.ecole221.common.service.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ClientDTOSerializer extends JsonSerializer<ClientDTO> {

    @Override
    public void serialize(ClientDTO clientDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", clientDTO.getId().toString());
        jsonGenerator.writeStringField("nomComplet", clientDTO.getNomComplet());
        jsonGenerator.writeStringField("tel", clientDTO.getTel());
        jsonGenerator.writeStringField("clientStatus", clientDTO.getClientStatus());
        jsonGenerator.writeStringField("compteStatus", clientDTO.getCompteStatus());
        if (clientDTO.getSolde() != null) jsonGenerator.writeStringField("solde", String.valueOf(clientDTO.getSolde()));
        // Omettre le champ "solde" dans la sérialisation
        jsonGenerator.writeEndObject();
    }
}
