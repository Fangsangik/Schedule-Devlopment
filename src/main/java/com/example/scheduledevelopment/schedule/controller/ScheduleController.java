package com.example.scheduledevelopment.schedule.controller;

import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import com.example.scheduledevelopment.schedule.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createSchedule(
            @RequestBody ScheduleDto scheduleDto) {
        try {
            ScheduleDto createSchedule = scheduleService.createSchedule(scheduleDto);
            return ResponseEntity.ok(createSchedule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("스케줄 생성 실패");
        }
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<?> findById(@PathVariable Long scheduleId) {
        try {
            ScheduleDto scheduleById = scheduleService.findScheduleById(scheduleId);
            return ResponseEntity.ok(scheduleById);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("스케줄 아이디를 찾을 수 없습니다.");
        }
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleDto scheduleDto) {
        try {
            ScheduleDto update = scheduleService.updateSchedule(scheduleId, scheduleDto);
            return ResponseEntity.ok(update);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("update에 실패했습니다.");
        }
    }

    @GetMapping("/")
    public Page<ScheduleDto> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return scheduleService.findAllSchedules(page, size);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(
            @PathVariable Long scheduleId) {
        try {
            scheduleService.deleteScheduleById(scheduleId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("스케줄에 삭제 되었습니다.");
        }
    }
}
