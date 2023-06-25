package com.example.vehicleServiceStation.producer;

import com.example.vehicleServiceStation.model.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class KafkaProducer {

    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;
    private final String kafkaTopic;

    public KafkaProducer(KafkaTemplate<String, NotificationMessage> kafkaTemplate,
                         @Value("${spring.kafka.topic}") String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    public void sendNotification(NotificationMessage message) {
        Message<NotificationMessage> resultMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopic)
                .build();
        kafkaTemplate.send(resultMessage);
    }
}

