package com.ecole221.client.paiement.service.config;

import com.ecole221.common.service.event.CompteEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerService {

    @Bean
    public ConsumerFactory<String, CompteEvent> EventConsumer() {

        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        map.put(JsonDeserializer.TYPE_MAPPINGS, "com.ecole221.common.service.event.CompteEvent:com.ecole221.common.service.event.CompteEvent");
        return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), new JsonDeserializer<>(CompteEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CompteEvent> EventListner(){
        ConcurrentKafkaListenerContainerFactory<String, CompteEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(EventConsumer());
        return factory;
    }
}
