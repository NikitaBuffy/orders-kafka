package ru.pominov.processing.service;

import org.springframework.kafka.annotation.KafkaListener;
import ru.pominov.processing.model.Order;
import ru.pominov.processing.model.OrderNotification;

public interface ProcessService {

    /**
     * @see ru.pominov.processing.service.ProcessServiceImpl#processOrder
     */
    void processOrder(Order order);

    @KafkaListener(topics = "new-order", groupId = "orders-kafka")
    void listener(Order order);

    /**
     * @see ru.pominov.processing.service.ProcessServiceImpl#sendOrderStatus
     */
    void sendOrderStatus(OrderNotification notification);
}
