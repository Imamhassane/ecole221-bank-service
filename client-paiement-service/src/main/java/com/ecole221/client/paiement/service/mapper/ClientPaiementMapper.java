package com.ecole221.client.paiement.service.mapper;

import com.ecole221.client.paiement.service.model.Client;
import com.ecole221.client.paiement.service.model.Paiement;
import com.ecole221.client.paiement.service.repository.ClientRepository;
import com.ecole221.client.paiement.service.service.ClientService;
import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.dto.PaiementDTO;
import com.ecole221.common.service.event.ClientStatus;
import com.ecole221.common.service.event.CompteStatus;
import com.ecole221.common.service.event.PaiementStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class ClientPaiementMapper{
    private final ClientRepository clientRepository;

    public ClientDTO clientToClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNomComplet(client.getNomComplet());
        dto.setTel(client.getTel());
        dto.setClientStatus(client.getClientStatus().name());
        dto.setCompteStatus(client.getCompteStatus().name());
        return dto;
    }


    public Client clientDTOToClient(ClientDTO clientDTO) {
        return Client.builder()
                .nomComplet(clientDTO.getNomComplet())
                .tel(clientDTO.getTel())
                .clientStatus(ClientStatus.valueOf(clientDTO.getClientStatus()))
                .compteStatus(CompteStatus.valueOf(clientDTO.getCompteStatus()))
                .build();
    }
    public PaiementDTO paiementToPaiementDTO(Paiement paiement) {
        return PaiementDTO.builder()
                .id(paiement.getId())
                .clientTel(paiement.getClient().getTel())
                .status(paiement.getPaiementStatus().toString())
                .montant(paiement.getMontant())
                .info(paiement.getInfo())
                .service(paiement.getService())
                .build();
    }
    public Paiement paiementDTOToPaiement(PaiementDTO paiement) {
        return Paiement.builder()
                .id(paiement.getId())
                .client(clientRepository.findClientByTel(paiement.getClientTel()).orElse(null))
                .paiementStatus(PaiementStatus.valueOf(paiement.getStatus()))
                .info(paiement.getInfo())
                .service(paiement.getService())
                .build();
    }




}
