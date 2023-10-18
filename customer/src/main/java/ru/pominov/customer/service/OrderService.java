package ru.pominov.customer.service;

import ru.pominov.customer.dto.OrderDto;
import ru.pominov.customer.model.Order;

public interface OrderService {

    void createOrder(String customerId, OrderDto orderDto);

    void sendOrder(Order order);
}
