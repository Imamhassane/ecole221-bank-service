package com.ecole221.client.paiement.service.config;

import com.ecole221.client.paiement.service.service.PaiementService;
import com.ecole221.common.service.event.CompteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaiementServiceKafka {
    private final PaiementService paiementService;

    @KafkaListener(topics = "compte-paiement-event-topic", groupId = "default", containerFactory = "EventListner")
    public void getResponse(CompteEvent compteEvent) {
        paiementService.updatePaiement(compteEvent);
    }
}
