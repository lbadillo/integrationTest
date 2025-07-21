package com.lbd.testliquibase.kafka;

import com.lbd.testliquibase.domain.KafkaDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class MessageProducer {


    private final KafkaTemplate<String, KafkaDTO> kafkaTemplate;

    public void sendMessage(String topic, KafkaDTO message) {
        kafkaTemplate.send(topic, message);
    }

}