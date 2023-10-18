package ru.pominov.customer.dto;

import ru.pominov.customer.model.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderMapper {

    public static Order toOrder(OrderDto orderDto) {
        return new Order(
                null,
                orderDto.getCustomerName(),
                orderDto.getCustomerPhone(),
                orderDto.getCustomerEmail(),
                orderDto.getItems(),
                orderDto.getDeliveryAddress(),
                LocalDateTime.parse(orderDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                null
        );
    }
}
