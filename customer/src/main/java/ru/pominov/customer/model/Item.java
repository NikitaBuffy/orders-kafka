package ru.pominov.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /**
     * Название товара
     */
    @NotBlank
    @Length(max = 255)
    private String title;

    /**
     * Цена товара
     */
    @NotBlank
    private int price;
}
