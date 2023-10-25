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

    /**
     * Метод занимается обработкой заказа.
     * Рассчитывает общую цену заказа, генерирует уникальный идентификатор заказа, устанавливает статусы по мере обработки.
     * Можно добавить иную обработку (проверка товаров в наличии, расчет налогов, подготовка к доставке и т.д.)
     *
     * @param order - данные заказа, полученные от Customer Service через Kafka по топику 'new-order'
     */
    @Override
    public void processOrder(Order order) {
        executorService.submit(() -> {
            order.setStatus(OrderStatus.PROCESSING);

            int totalAmount = 0;
            for (Item item : order.getItems()) {
                totalAmount += item.getPrice();
            }
            order.setTotalAmount(totalAmount);
            order.setStatus(OrderStatus.DELIVERY_READY);

            orderRepository.save(order);
        });

        executorService.shutdown();
    }

    @Override
    public void listener(Order order) {
        log.info("Received {} from Kafka topic 'new-order'", order);
        processOrder(order);
    }
}
