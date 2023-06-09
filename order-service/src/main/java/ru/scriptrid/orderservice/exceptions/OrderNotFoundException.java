package ru.scriptrid.orderservice.exceptions;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {
    private final long orderId;

    public OrderNotFoundException(long orderId) {
        this.orderId = orderId;
    }
}
