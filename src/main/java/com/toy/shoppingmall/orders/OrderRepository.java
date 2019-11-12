package com.toy.shoppingmall.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    /**
     * 동적 쿼리 작성법
     * 1. string
     * 2. JPA Criteria  -> JPA 표준 스펙으로 있지만, 유지보수가 너무 어려워 사용 X
     * (실무) 3. QueryDSL
     *
     * @param orderSearch
     * @return
     */
    List<Order> findAllByCritera(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";

        boolean isFirst = true;
        if (orderSearch.getOrderStatus() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            }
            jpql += " o.status = :status";
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirst) {
                jpql += " where";
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> orderTypedQuery = entityManager.createQuery(jpql, Order.class)
                                                         .setMaxResults(1_000);

        if (orderSearch.getOrderStatus() != null) {
            orderTypedQuery.setParameter("status", orderSearch.getOrderStatus());
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            orderTypedQuery.setParameter("name", orderSearch.getMemberName());
        }
//                            .setFirstResult(100)        // 100부터

        return orderTypedQuery.getResultList();
    }
}
