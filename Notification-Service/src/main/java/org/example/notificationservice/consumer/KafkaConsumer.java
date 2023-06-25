package org.example.notificationservice.consumer;

import org.example.notificationservice.model.NotificationMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveNotification(NotificationMessage message) {
        // Process the received notification message
        System.out.println("Received notification Message : " + message.getMessage());
        // Add your logic to handle the received message
    }
}

