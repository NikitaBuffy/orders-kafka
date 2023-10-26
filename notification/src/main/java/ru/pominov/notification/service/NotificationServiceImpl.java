package ru.pominov.notification.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pominov.notification.model.OrderNotification;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    /**
     * Присылает уведомление пользователю о статусе его заказа
     * Метод отправляет уведомление в консоль для примера
     *
     * @param notification объект уведомления {@link ru.pominov.notification.model.OrderNotification}
     */
    public void notifyCustomer(OrderNotification notification) {
        String status = "";
        switch(notification.getStatus()) {
            case "DELIVERY_READY":
                status = "Готов к отправке.";
                break;
            case "IN_DELIVERY":
                status = "Отправлен.";
                break;
            case "CANCELED":
                status = "Отменен.";
                break;
            case "PROCESSING":
                status = "В обработке.";
                break;
        }

        System.out.println("Изменение статуса заказа №" + notification.getOrderId()
                + ". Новый статус: " + status);
        log.info("Notification sent. Customer ID: {}, order ID: {}, status: {}.",
                notification.getCustomerId(), notification.getOrderId(), notification.getStatus());
    }

    @Override
    public void listener(OrderNotification notification) {
        log.info("Received {} from Kafka topic 'notification'", notification);
        notifyCustomer(notification);
    }
}
