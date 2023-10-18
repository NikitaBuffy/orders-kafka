package ru.pominov.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    /**
     * Идентификатор пользователя
     */
    private String customerId;

    private String customerName;

    private String customerPhone;

    private String customerEmail;

    private List<Item> items;

    private String deliveryAddress;

    private LocalDateTime date;

    /**
     * Статус заказа (при создании устанавливается CREATED)
     */
    private String status;
}
