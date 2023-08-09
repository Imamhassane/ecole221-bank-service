package com.ecole221.compte.transaction.service.config;

import com.ecole221.common.service.event.ClientEvent;
import com.ecole221.common.service.event.CompteEvent;
import com.ecole221.common.service.event.ServiceEvent;
import com.ecole221.compte.transaction.service.service.CompteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompteServiceServiceKafka {
    private static final String PAIEMENT_COMPTE_TOPIC = "compte-paiement-event-topic";

    private final CompteService compteService;
    private final KafkaTemplate<String, CompteEvent> kafkaTemplate;

    @KafkaListener(topics = "service-event-topic", groupId = "default", containerFactory = "eventListenerService")
    public void consumeMessageService( ServiceEvent serviceEvent){
        log.info("message consumed {}", serviceEvent.getEventId());
        try {
            CompteEvent compteEvent = compteService.checkSolde(serviceEvent);
            kafkaTemplate.send(PAIEMENT_COMPTE_TOPIC, compteEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
