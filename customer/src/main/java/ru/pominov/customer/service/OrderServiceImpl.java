package ru.pominov.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.pominov.customer.dto.OrderDto;
import ru.pominov.customer.dto.OrderMapper;
import ru.pominov.customer.exception.ValidationException;
import ru.pominov.customer.model.Item;
import ru.pominov.customer.model.Order;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    /**
     * Метод преобразует полученные данные в объект {@link ru.pominov.customer.model.Order}.
     * Проводит валидацию товаров в заказе
     *
     * @param customerId идентификатор клиента
     * @param orderDto заказ
     */
    @Override
    public void createOrder(Long customerId, OrderDto orderDto) {
        itemsValidation(orderDto.getItems());

        Order order = OrderMapper.toOrder(orderDto);
        order.setCustomerId(customerId);
        order.setStatus("CREATED");

        log.info("Received and validated new order: {}", order);

        sendOrder(order);
    }

    /**
     * Отправка заказа в топик Kafka
     *
     * @param order объект заказа {@link ru.pominov.customer.model.Order}
     */
    @Override
    public void sendOrder(Order order) {
        kafkaTemplate.send(kafkaTopic, order);
        log.info("Sent {} to Kafka topic {}", order, kafkaTopic);
    }

    void itemsValidation(List<Item> items) {
        for (Item item : items) {
            if (item.getTitle() == null || item.getTitle().length() > 255 || item.getTitle().isBlank()) {
                throw new ValidationException("Item title must not be blank or length must not be greater than 255.");
            }
            if (item.getPrice() == null || item.getPrice() < 0) {
                throw new ValidationException("Item price must not be null or negative.");
            }
        }
    }
}
