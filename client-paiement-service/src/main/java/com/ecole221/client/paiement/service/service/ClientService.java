package com.ecole221.client.paiement.service.service;

import com.ecole221.client.paiement.service.exception.client.ClientException;
import com.ecole221.client.paiement.service.exception.client.ClientNotFoundException;
import com.ecole221.client.paiement.service.mapper.ClientPaiementMapper;
import com.ecole221.client.paiement.service.model.Client;
import com.ecole221.client.paiement.service.repository.ClientRepository;
import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.event.ClientEvent;
import com.ecole221.common.service.event.ClientStatus;
import com.ecole221.common.service.event.CompteStatus;
import com.ecole221.common.service.event.CompteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Data;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class ClientService {
    private static final String CLIENT_TOPIC = "client-event-topic";
    private final ClientRepository clientRepository;
    private final ClientPaiementMapper mapper;
    private final KafkaTemplate<String, ClientEvent> kafkaTemplate;
    private final Utils utils;


    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(mapper::clientToClientDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Client saveClient(ClientDTO clientDTO) {
        //vvalidation
        utils.checkData(clientDTO.getTel());
        clientExists(clientDTO.getTel());

        clientDTO.setClientStatus("CREATED");
        clientDTO.setCompteStatus("INIT");
        Client client = clientRepository.save(mapper.clientDTOToClient(clientDTO));
        clientDTO.setId(client.getId());
        ClientEvent clientEvent = new ClientEvent(clientDTO, ClientStatus.CREATED);
        kafkaTemplate.send(CLIENT_TOPIC, clientEvent);
        return client;
    }

    @Transactional
    public void updateClient(CompteEvent event) {
        clientRepository.findById(event.getCompteDTO().getClientId()).ifPresent(client -> {
            boolean isSaved = CompteStatus.UPDATED.equals(event.getCompteStatus());
            ClientStatus clientStatus = isSaved ? ClientStatus.COMPLETE: ClientStatus.ERREUR_CREATION;
            log.info(event.getCompteStatus().toString());
            client.setClientStatus(clientStatus);
            client.setCompteStatus(CompteStatus.UPDATED);
        });
    }

    public Client findClientByTel(String tel) {
        return clientRepository.findClientByTel(tel).orElseThrow(()->new ClientNotFoundException("Client not found!"));
    }

    public void clientExists(String tel){
        boolean isExist = clientRepository.findAll().stream()
                .noneMatch(client -> Objects.equals(client.getTel(), tel));
        if (!isExist) throw new ClientException("Ce numéro existe!");
    }

    @Transactional
    public Client depotInCompte(ClientDTO clientDTO){
        utils.checkData(clientDTO.getTel());
        if (clientDTO.getSolde().compareTo(BigDecimal.ZERO) < 0) throw new ClientException("Solde invalide!");
        Client client = findClientByTel(clientDTO.getTel());
        clientDTO.setId(client.getId());
        ClientEvent clientEvent = new ClientEvent(clientDTO, ClientStatus.CREATED);
        kafkaTemplate.send(CLIENT_TOPIC, clientEvent);
        return client;
    }
}
