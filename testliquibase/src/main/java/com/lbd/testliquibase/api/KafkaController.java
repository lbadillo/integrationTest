package com.lbd.testliquibase.api;

import com.lbd.testliquibase.domain.KafkaDTO;
import com.lbd.testliquibase.kafka.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final MessageProducer messageProducer;
    @Value("${spring.kafka.topic1}")
    private String topic;

    @PostMapping("/send")
    public String sendMessage(@RequestBody KafkaDTO message) {
        messageProducer.sendMessage(topic, message);
        return "Message sent: " + message.toString();
    }

}
