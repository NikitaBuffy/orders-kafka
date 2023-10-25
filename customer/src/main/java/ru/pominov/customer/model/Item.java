package ru.pominov.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /**
     * Название товара
     */
    private String title;

    /**
     * Цена товара
     */
    private Integer price;
}
