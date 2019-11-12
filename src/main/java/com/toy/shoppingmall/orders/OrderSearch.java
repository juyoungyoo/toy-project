package com.toy.shoppingmall.orders;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus; // 주문의 상태 ORDER, CANCEL
}
