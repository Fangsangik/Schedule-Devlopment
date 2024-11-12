package com.example.scheduledevelopment.schedule.service;

import com.example.scheduledevelopment.member.entity.Member;
import com.example.scheduledevelopment.member.mapper.MemberMapper;
import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import com.example.scheduledevelopment.schedule.mapper.ScheduleMapper;
import com.example.scheduledevelopment.schedule.repository.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final MemberMapper memberMapper;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, MemberMapper memberMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.memberMapper = memberMapper;
    }

    //생성
    @Transactional
    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDto);
        Schedule createSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toDto(createSchedule);
    }

    //update
    @Transactional
    public ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto) {
        Schedule findById = validateOfId(id);

        if (scheduleDto.getAuthor() != null) {
            findById.updateAuthor(scheduleDto.getAuthor());
        }
        if (scheduleDto.getTitle() != null) {
            findById.updateTitle(scheduleDto.getTitle());
        }
        if (scheduleDto.getTodo() != null) {
            findById.updateTodo(scheduleDto.getTodo());
        }
        if (scheduleDto.getMemberDto() != null) {
            Member member = memberMapper.toEntity(scheduleDto.getMemberDto());
            findById.updateMember(member); // 엔티티 메서드를 통해 멤버 수정
        }

        return scheduleMapper.toDto(findById);
    }

    //조회
    @Transactional(readOnly = true)
    public ScheduleDto findScheduleById(Long id) {
        Schedule findById = validateOfId(id);
        return scheduleMapper.toDto(findById);
    }

    //삭제
    @Transactional
    public void deleteScheduleById(Long id) {
        Schedule findById = validateOfId(id);
        scheduleRepository.deleteById(findById.getId());
    }

    @Transactional(readOnly = true)
    public Page<ScheduleDto> findAllSchedules(int page, int size) {
        size = (size > 0) ? size : 10;
        PageRequest pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        return schedules.map(scheduleMapper::toDto);
    }

    private Schedule validateOfId(Long id) {
        Schedule findById = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("스케줄을 찾지 못했습니다."));
        return findById;
    }
}