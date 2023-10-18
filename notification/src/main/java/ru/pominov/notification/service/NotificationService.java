package ru.pominov.notification.service;

import org.springframework.kafka.annotation.KafkaListener;

public interface NotificationService {

    void notifyCustomer(String customerId, Long orderId, String orderStatus);

    @KafkaListener(topics = "notification", groupId = "orders-kafka")
    void listener(String customerId, Long orderId, String orderStatus);
}
