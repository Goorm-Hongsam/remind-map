package com.remind.map.member.repository;

import com.remind.map.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findMemberById(Long id) {

        return em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }


}
