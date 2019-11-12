package com.toy.shoppingmall.orders;

import com.toy.shoppingmall.items.NotEnoughStockException;
import com.toy.shoppingmall.items.domain.Book;
import com.toy.shoppingmall.members.Address;
import com.toy.shoppingmall.members.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @DisplayName("상품주문")
    @Test
    public void order() {
        // given
        Member member = createMember();

        Book book = createBook("시골 JPA", 10_000, 10);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(getOrder.getTotalPrice()).isEqualTo(10_000 * orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(8);
    }

    @DisplayName("상품주문 재고수량 초과")
    @Test
    public void order_exception() {
        // given
        Member member = createMember();
        int stockQuantity = 10;
        Book book = createBook("시골 JPA", 10_000, stockQuantity);
        int orderCount = stockQuantity + 1;

        // when & then
        assertThatExceptionOfType(NotEnoughStockException.class)
                .isThrownBy(() -> orderService.order(member.getId(), book.getId(), orderCount));
    }

    @DisplayName("주문 취소")
    @Test
    void cancle() {
        // given
        Member member = createMember();
        Book item = createBook("시골JPA", 10_000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order getOrder = orderRepository.findOne(orderId);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCLE);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setAuthor(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}