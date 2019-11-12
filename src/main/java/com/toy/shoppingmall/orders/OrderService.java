package com.toy.shoppingmall.orders;

import com.toy.shoppingmall.items.ItemRepository;
import com.toy.shoppingmall.items.domain.Item;
import com.toy.shoppingmall.members.Member;
import com.toy.shoppingmall.members.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    /**
     * 주문
     */
    @Transactional
    Long order(Long memberId, Long itemId, int count) {
        // Entity search
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.of(item, item.getPrice(), count);
        Order order = Order.of(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 취소
     */
    @Transactional
    void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancle();
    }

/*
    // 검색
    public List<Order> findOrders(OrderSearch orderId) {
        return null;
    }
    */
}
