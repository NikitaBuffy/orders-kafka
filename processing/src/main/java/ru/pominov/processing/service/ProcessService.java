package ru.pominov.processing.service;

import org.springframework.kafka.annotation.KafkaListener;
import ru.pominov.processing.model.Order;

public interface ProcessService {

    void processOrder(Order order);

    @KafkaListener(topics = "new-order", groupId = "orders-kafka")
    void listener(Order order);
}
