package com.example.scheduledevelopment.schedule.repository;

import com.example.scheduledevelopment.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    /**
     * QueryDsl을 사용한 이유
     * JPA명명 규칙이 늘어나면, 오타라던지, 정확한 의미를 파악하기 어렵기도 하고,
     * 자동으로 join 해서 가져온다고 하지만, QueryDSL을 사용함으로 join을 직접 지정해서 사용 할 수 있기 때문
     */
    Page<Schedule> findAll(Pageable pageable);
}
