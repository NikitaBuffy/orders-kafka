package ru.pominov.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pominov.customer.model.Item;

import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    /**
     * Имя клиента
     */
    @NotBlank
    private String customerName;

    /**
     * Телефон клиента
     */
    @NotBlank
    @Size(min = 11, max = 16)
    private String customerPhone;

    /**
     * Электронная почта клиента
     */
    @Email
    private String customerEmail;

    /**
     * Список товаров
     */
    @NotNull
    @NotEmpty
    private List<Item> items;

    /**
     * Адрес доставки
     */
    @Size(min = 10, max = 500)
    private String deliveryAddress;

    /**
     * Дата доставки (в формате "yyyy-MM-dd HH:mm")
     */
    @NotBlank
    private String date;
}
