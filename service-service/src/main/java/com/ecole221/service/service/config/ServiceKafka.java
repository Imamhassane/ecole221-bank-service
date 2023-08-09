package com.ecole221.service.service.config;

import com.ecole221.common.service.event.CompteEvent;
import com.ecole221.common.service.event.PaiementEvent;
import com.ecole221.common.service.event.ServiceEvent;
import com.ecole221.service.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceKafka {
    private static final String SERVICE_TOPIC = "service-event-topic";
    private final ServiceService service;
    private final KafkaTemplate<String, ServiceEvent> kafkaTemplate;

    @KafkaListener(topics = "paiement-event-topic", groupId = "default", containerFactory = "EventListner")
    public void getResponse(PaiementEvent paiementEvent) {

        log.info("message consumed {}", paiementEvent.getEventId());
        try {
            ServiceEvent serviceEvent = service.serviceIsActif(paiementEvent);
            kafkaTemplate.send(SERVICE_TOPIC, serviceEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
