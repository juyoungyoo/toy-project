package com.toy.shoppingmall.members;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager entityManager;

    void save(Member member) {
        entityManager.persist(member);
    }

    public Member findOne(Long id) {
        return entityManager.find(Member.class, id);
    }

    List<Member> findAll() {
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    List<Member> findByName(String name) {
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
