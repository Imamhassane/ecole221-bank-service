package com.ecole221.client.paiement.service.service;

import com.ecole221.client.paiement.service.mapper.ClientPaiementMapper;
import com.ecole221.client.paiement.service.model.Client;
import com.ecole221.client.paiement.service.model.Paiement;
import com.ecole221.client.paiement.service.repository.PaiementRepository;
import com.ecole221.common.service.dto.PaiementDTO;
import com.ecole221.common.service.event.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class PaiementService {
    private static final String PAIEMENT_TOPIC = "paiement-event-topic";
    private static final String PAIEMENT_TRANSACTION_TOPIC = "paiement-transaction-event-topic";
    private final PaiementRepository paiementRepository;
    private final ClientPaiementMapper mapper;
    private final KafkaTemplate<String, PaiementEvent> kafkaTemplate;
    private final ClientService clientService;

    public List<PaiementDTO> getAllPaiements() {
        return paiementRepository.findAll().
                stream()
                .map(mapper::paiementToPaiementDTO)
                .toList();
    }

    @Transactional
    public Paiement savePaiement(PaiementDTO paiementDTO) {
        Client client = clientService.findClientByTel(paiementDTO.getClientTel());
        paiementDTO.setStatus("INIT");
        paiementDTO.setInfo("paiement en cours de traitement");

        Paiement paiement = paiementRepository.save(mapper.paiementDTOToPaiement(paiementDTO));
        paiementDTO.setId(paiement.getId());

        PaiementEvent paiementEvent = new PaiementEvent(paiementDTO,PaiementStatus.INIT,
                mapper.clientToClientDTO(client));
        kafkaTemplate.send(PAIEMENT_TOPIC, paiementEvent);
        return paiement;
    }

    @Transactional
    public void updatePaiement(CompteEvent compteEvent) {
        paiementRepository.findById(compteEvent.getPaiementDTO().getId()).ifPresent(paiement -> {
            PaiementStatus paiementStatus ;
            String info ;
            if (CompteStatus.ERREUR_SERVICE.equals(compteEvent.getCompteStatus())){
                paiementStatus = PaiementStatus.ERREUR_PAIEMENT;
                info = "Paiement non effectué, le service est inactif!";
            }else if(CompteStatus.ERREUR_SOLDE.equals(compteEvent.getCompteStatus())){
                paiementStatus = PaiementStatus.ERREUR_PAIEMENT;
                info = "Paiement non effectué solde insuffisant, veuillez rechargé votre compte!";
            } else {
                paiementStatus = PaiementStatus.COMPLETE;
                info = "Paiement effectué avec succés!";
            }
            paiement.setPaiementStatus(paiementStatus);
            paiement.setInfo(info);
            paiement.setMontant(compteEvent.getPaiementDTO().getMontant());

           PaiementEvent paiementEvent = new PaiementEvent
                  (mapper.paiementToPaiementDTO(paiement), paiementStatus, mapper.clientToClientDTO(paiement.getClient()));

            kafkaTemplate.send(PAIEMENT_TRANSACTION_TOPIC, paiementEvent);
        });
    }

}
