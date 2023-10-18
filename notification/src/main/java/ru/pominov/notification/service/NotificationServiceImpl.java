package ru.pominov.notification.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    // TODO: 18.10.2023 Реализовать метод уведомлений
    public void notifyCustomer(String customerId, Long orderId, String orderStatus) {
        System.out.println("Заказ №" + orderId + " имеет статус " + orderStatus);
    }

    // TODO: 18.10.2023 Проверить успешный пул из кафки
    @Override
    public void listener(String customerId, Long orderId, String orderStatus) {
        notifyCustomer(customerId, orderId, orderStatus);
    }
}
