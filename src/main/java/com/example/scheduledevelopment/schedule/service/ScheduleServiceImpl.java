package com.example.scheduledevelopment.schedule.service;

import com.example.scheduledevelopment.comment.entity.QComment;
import com.example.scheduledevelopment.member.dto.MemberDto;
import com.example.scheduledevelopment.member.entity.Member;
import com.example.scheduledevelopment.member.entity.QMember;
import com.example.scheduledevelopment.member.mapper.MemberMapper;
import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import com.example.scheduledevelopment.schedule.entity.QSchedule;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import com.example.scheduledevelopment.schedule.mapper.ScheduleMapper;
import com.example.scheduledevelopment.schedule.repository.ScheduleRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.scheduledevelopment.comment.entity.QComment.comment;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final MemberMapper memberMapper;
    private final JPAQueryFactory queryFactory;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, MemberMapper memberMapper, JPAQueryFactory queryFactory) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.memberMapper = memberMapper;

        this.queryFactory = queryFactory;
    }

    //생성
    @Override
    @Transactional
    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDto);
        Schedule createSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toDto(createSchedule);
    }

    //update
    @Override
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
    @Override
    @Transactional(readOnly = true)
    public ScheduleDto findScheduleById(Long id) {
        Schedule findById = validateOfId(id);

        long commentCount = queryFactory.select(
                comment.count())
                .from(comment)
                .where(comment.schedule.id.eq(id))
                .fetchOne();

        ScheduleDto scheduleDto = scheduleMapper.toDto(findById);
        scheduleDto.setCommentCount(commentCount);

        return scheduleDto;
    }

    //삭제
    @Override
    @Transactional
    public void deleteScheduleById(Long id) {
        Schedule findById = validateOfId(id);
        scheduleRepository.deleteById(findById.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ScheduleDto> findAllSchedules(int page, int size) {
        size = (size > 0) ? size : 10;
        Pageable pageable = PageRequest.of(page, size);

        QSchedule schedule = QSchedule.schedule;
        QMember member = QMember.member;
        QComment comment = QComment.comment;

        List<ScheduleDto> results = queryFactory
                .select(Projections.constructor(ScheduleDto.class,
                        schedule.id,
                        schedule.author,
                        schedule.title,
                        schedule.todo,
                        schedule.createdAt,
                        schedule.updatedAt,
                        Projections.constructor(MemberDto.class, // memberDto 매핑 추가
                                member.id,
                                member.name,
                                member.email,
                                member.createdAt,
                                member.updatedAt
                        ),
                        comment.count().as("commentCount")
                ))
                .from(schedule)
                .leftJoin(comment).on(comment.schedule.id.eq(schedule.id))
                .leftJoin(member).on(member.id.eq(schedule.member.id))
                .groupBy(schedule.id, member.id)
                .orderBy(schedule.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(
                schedule.count())
                .from(schedule)
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }

    private Schedule validateOfId(Long id) {
        Schedule findById = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("스케줄을 찾지 못했습니다."));
        return findById;
    }
}
