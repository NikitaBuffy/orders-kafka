package ru.pominov.notification.service;

import org.springframework.kafka.annotation.KafkaListener;
import ru.pominov.notification.model.OrderNotification;

public interface NotificationService {

    /**
     * @see ru.pominov.notification.service.NotificationServiceImpl#notifyCustomer
     */
    void notifyCustomer(OrderNotification notification);

    @KafkaListener(topics = "notification", groupId = "orders-kafka")
    void listener(OrderNotification notification);
}
