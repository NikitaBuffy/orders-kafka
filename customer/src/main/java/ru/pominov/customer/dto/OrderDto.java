package ru.pominov.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pominov.customer.model.Item;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

// TODO: 18.10.2023 Проверить валидацию данных и настроить обработку исключений 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    /**
     * Имя клиента
     */
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
    @NotBlank
    private List<Item> items;

    /**
     * Адрес доставки
     */
    @Size(min = 10, max = 500)
    private String deliveryAddress;

    /**
     * Дата доставки (в формате "yyyy-MM-dd HH:mm")
     */
    @NotNull
    @NotBlank
    private String date;
}
