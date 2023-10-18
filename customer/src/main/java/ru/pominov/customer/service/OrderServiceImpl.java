package ru.pominov.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.pominov.customer.dto.OrderDto;
import ru.pominov.customer.dto.OrderMapper;
import ru.pominov.customer.model.Order;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Override
    public void createOrder(String customerId, OrderDto orderDto) {
        Order order = OrderMapper.toOrder(orderDto);
        order.setCustomerId(customerId);
        order.setStatus("CREATED");
        log.info("Received and validated new order: {}", order);

        sendOrder(order);
    }

    @Override
    public void sendOrder(Order order) {
        kafkaTemplate.send(kafkaTopic, order);
        log.info("Sent {} to Kafka topic {}", order, kafkaTopic);
    }
}
