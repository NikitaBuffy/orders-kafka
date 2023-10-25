package ru.pominov.processing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.pominov.processing.model.Item;
import ru.pominov.processing.model.Order;
import ru.pominov.processing.model.OrderNotification;
import ru.pominov.processing.model.OrderStatus;
import ru.pominov.processing.repository.OrderRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final KafkaTemplate<String, OrderNotification> kafkaTemplate;
    @Value("${kafka.topic}")
    private String kafkaTopic;
    private final OrderRepository orderRepository;

    /**
     * Метод занимается обработкой заказа.
     * Рассчитывает общую цену заказа, генерирует уникальный идентификатор заказа, устанавливает статусы по мере обработки.
     * Можно добавить иную обработку (проверка товаров в наличии, расчет налогов, подготовка к доставке и т.д.)
     *
     * @param order - данные заказа, полученные от Customer Service через Kafka по топику 'new-order'
     */
    @Override
    public void processOrder(Order order) {
        Order createdOrder = orderRepository.save(order);
        createdOrder.setStatus(OrderStatus.PROCESSING);

        // Отправка статуса "В обработке"
        sendOrderStatus(new OrderNotification(createdOrder.getId(), createdOrder.getCustomerId(), createdOrder.getStatus().name()));

        // Имитация обработки заказа
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int totalAmount = 0;
        for (Item item : order.getItems()) {
            totalAmount += item.getPrice();
        }
        createdOrder.setTotalAmount(totalAmount);
        createdOrder.setStatus(OrderStatus.DELIVERY_READY);
        orderRepository.save(createdOrder);

        // Отправка статуса "Готово к отправке"
        sendOrderStatus(new OrderNotification(createdOrder.getId(), createdOrder.getCustomerId(), createdOrder.getStatus().name()));
    }

    @Override
    public void listener(Order order) {
        log.info("Received {} from Kafka topic 'new-order'", order);
        processOrder(order);
    }

    @Override
    public void sendOrderStatus(OrderNotification notification) {
        kafkaTemplate.send(kafkaTopic, notification);
        log.info("Sent {} to Kafka topic {}", notification, kafkaTopic);
    }
}
