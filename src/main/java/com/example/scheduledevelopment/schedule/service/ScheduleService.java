package com.example.scheduledevelopment.schedule.service;

import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import org.springframework.data.domain.Page;

public interface ScheduleService {
    ScheduleDto createSchedule(ScheduleDto scheduleDto);

    ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto);

    ScheduleDto findScheduleById(Long id);

    void deleteScheduleById(Long id);

    Page<ScheduleDto> findAllSchedules(int page, int size);
}
