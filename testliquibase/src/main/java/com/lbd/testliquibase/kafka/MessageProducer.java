package com.lbd.testliquibase.kafka;

import com.lbd.testliquibase.domain.KafkaDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class MessageProducer {


    private final KafkaTemplate<String, KafkaDTO> kafkaTemplate;
    @Value("${spring.kafka.topic1}")
    private String topic;

    public void sendMessage(KafkaDTO message) {
        kafkaTemplate.send(topic, message);
    }

}