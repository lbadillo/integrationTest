package com.lbd.testliquibase.kafka;

import com.lbd.testliquibase.domain.KafkaDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "${spring.kafka.topic1}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(KafkaDTO message) {
        System.out.println("Received message: " + message.toString());
    }

}