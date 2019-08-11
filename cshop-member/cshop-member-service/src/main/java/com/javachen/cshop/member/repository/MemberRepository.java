package com.javachen.cshop.member.repository;

import com.javachen.cshop.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface MemberRepository extends JpaRepository<Member, Long> {
    @Modifying
    @Query("delete from Member where id = ?1")
    void deleteById(Long id);

    Member findByPhone(String phone);

    Member findByOpenId(String openId);

    Member findByUsername(String username);

    Member findByEmail(String email);

    @Query("select u from Member u")
    Page<Member> findAll(Pageable pageable);
}
