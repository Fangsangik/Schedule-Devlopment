package com.example.scheduledevelopment.member.repository;

import com.example.scheduledevelopment.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
