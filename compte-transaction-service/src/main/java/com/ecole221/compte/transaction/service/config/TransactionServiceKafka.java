package com.ecole221.compte.transaction.service.config;

import com.ecole221.common.service.event.ClientEvent;
import com.ecole221.common.service.event.CompteEvent;
import com.ecole221.common.service.event.PaiementEvent;
import com.ecole221.common.service.event.ServiceEvent;
import com.ecole221.compte.transaction.service.service.CompteService;
import com.ecole221.compte.transaction.service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionServiceKafka {
    private final TransactionService service;

    @KafkaListener(topics = "paiement-transaction-event-topic", groupId = "default", containerFactory = "EventListnerTransaction")
    public void consumeMessage(PaiementEvent paiementEvent){
        log.info("message consumed {}", paiementEvent.getEventId());
        try {
            service.saveTransaction(paiementEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
