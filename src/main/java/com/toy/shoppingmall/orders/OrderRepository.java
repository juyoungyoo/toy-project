package com.toy.shoppingmall.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager entityManager;

    void save(Order order) {
        entityManager.persist(order);
    }

    Order findOne(Long id) {
        return entityManager.find(Order.class, id);
    }

//    List<Order> findAll(OrderSearch orderSearch) {
//        return null;
//    }
}
