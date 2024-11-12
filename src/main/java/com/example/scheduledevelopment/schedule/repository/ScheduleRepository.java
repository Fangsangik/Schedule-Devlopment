package com.example.scheduledevelopment.schedule.repository;

import com.example.scheduledevelopment.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    //N+1 문제를 방지하기 위해 @EntityGraph를 사용하여 연관된 엔티티를 페치
    @EntityGraph(attributePaths = {"comments", "member"})
    Page<Schedule> findAll(Pageable pageable);
}
