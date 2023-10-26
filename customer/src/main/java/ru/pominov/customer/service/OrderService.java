package ru.pominov.customer.service;

import ru.pominov.customer.dto.OrderDto;
import ru.pominov.customer.model.Order;

public interface OrderService {

    /**
     * @see ru.pominov.customer.service.OrderServiceImpl#createOrder
     */
    void createOrder(Long customerId, OrderDto orderDto);

    /**
     * @see ru.pominov.customer.service.OrderServiceImpl#sendOrder
     */
    void sendOrder(Order order);
}
