package ru.pominov.processing.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    CREATED,
    PROCESSING,
    DELIVERY_READY,
    IN_DELIVERY,
    CANCELED
}
