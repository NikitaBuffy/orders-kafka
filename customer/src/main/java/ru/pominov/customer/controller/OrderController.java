package ru.pominov.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pominov.customer.dto.OrderDto;
import ru.pominov.customer.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/order/create")
public class OrderController {

    private final OrderService orderService;

    /**
     * Создает заказ и отправляет его в сервис обработки
     *
     * @param customerId - идентификатор клиента
     * @param orderDto - данные заказа
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader("X-Customer-Id") String customerId, @Validated @RequestBody OrderDto orderDto) {
        orderService.createOrder(customerId, orderDto);
    }
}
