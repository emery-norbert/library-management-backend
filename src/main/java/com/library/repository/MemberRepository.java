package com.library.repository;

import com.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Custom queries for filtering members by name or email
    List<Member> findByNameContaining(String name);
    List<Member> findByEmailContaining(String email);
    List<Member> findByNameContainingAndEmailContaining(String name, String email);
}