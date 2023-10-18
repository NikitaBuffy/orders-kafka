package ru.pominov.processing.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pominov.processing.model.Item;
import ru.pominov.processing.model.Order;
import ru.pominov.processing.model.OrderStatus;
import ru.pominov.processing.repository.OrderRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
@AllArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final OrderRepository orderRepository;

    // TODO: 18.10.2023 Доработать метод
    @Override
    public void processOrder(Order order) {
        executorService.submit(() -> {
            order.setStatus(OrderStatus.PROCESSING);

            int totalAmount = 0;
            for (Item item : order.getItems()) {
                totalAmount += item.getPrice();
            }
            order.setTotalAmount(totalAmount);

            String address = order.getDeliveryAddress();
            if (!address.startsWith("Санкт-Петербург")) {
                log.debug("Мы доставляем товары только по Санкт-Петербургу");
                order.setStatus(OrderStatus.CANCELED);
            }

            Order createdOrder = orderRepository.save(order);
            createdOrder.setStatus(OrderStatus.DELIVERY_READY);
        });

        executorService.shutdown();
    }

    // TODO: 18.10.2023 Проверить успешный пул из кафки
    @Override
    public void listener(Order order) {
        System.out.println(order);
        processOrder(order);
    }
}
