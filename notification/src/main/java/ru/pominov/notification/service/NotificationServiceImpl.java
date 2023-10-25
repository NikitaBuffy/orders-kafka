package ru.pominov.notification.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pominov.notification.model.OrderNotification;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    // TODO: 18.10.2023 Реализовать метод уведомлений
    public void notifyCustomer(String customerId, Long orderId, String orderStatus) {
        System.out.println("Заказ №" + orderId + " имеет статус " + orderStatus);
    }

    @Override
    public void listener(OrderNotification notification) {
        // Проверка прослушивания топика
        System.out.println(notification);
    }
}
