package com.toy.shoppingmall.items;

public class NotEnoughStockException extends IllegalStateException {

    public NotEnoughStockException(String message) {
        super(message);
    }
}
