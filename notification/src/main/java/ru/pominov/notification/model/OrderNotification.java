package ru.pominov.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderNotification {

    /**
     * Идентификатор заказа
     */
    private Long orderId;

    /**
     * Идентификатор покупателя
     */
    private Long customerId;

    /**
     * Статус заказа
     */
    private String status;
}
