package ru.pominov.processing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.List;

// TODO: 18.10.2023 Настроить базу данных
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Transient
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "amount")
    private int totalAmount;

    @Transient
    private List<Item> items;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Future
    @Column(name = "delivery_date")
    private LocalDateTime date;

    @Column(name = "status")
    private OrderStatus status;
}
