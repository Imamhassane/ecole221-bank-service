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
public class CompteServiceClientKafka {
    private static final String CLIENT_COMPTE_TOPIC = "compte-client-event-topic";

    private final CompteService compteService;
    private final KafkaTemplate<String, CompteEvent> kafkaTemplate;

    @KafkaListener(topics = "client-event-topic", groupId = "default", containerFactory = "eventListenerClient")
    public void consumeMessageClient(ClientEvent clientEvent){
        log.info("message consumed {}", clientEvent.getEventId());
        try {
            CompteEvent compteEvent = compteService.saveCompte(clientEvent);
            kafkaTemplate.send(CLIENT_COMPTE_TOPIC, compteEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
