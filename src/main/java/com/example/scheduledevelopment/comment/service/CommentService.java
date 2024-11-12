package com.example.scheduledevelopment.comment.service;

import com.example.scheduledevelopment.comment.dto.CommentDto;
import com.example.scheduledevelopment.comment.entity.Comment;
import com.example.scheduledevelopment.comment.mapper.CommentMapper;
import com.example.scheduledevelopment.comment.repositroy.CommentRepository;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import com.example.scheduledevelopment.schedule.mapper.ScheduleMapper;
import com.example.scheduledevelopment.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleService scheduleService;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, ScheduleMapper scheduleMapper, ScheduleService scheduleService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.scheduleMapper = scheduleMapper;
        this.scheduleService = scheduleService;
    }

    //create
    @Transactional
    public CommentDto createComment(CommentDto commentDto) {
        Schedule schedule = commentDto.getScheduleDto() != null
                ? scheduleMapper.toEntity(commentDto.getScheduleDto())
                : null;

        Comment comment = Comment.builder()
                .commentDetails(commentDto.getCommentDetails())
                .schedule(schedule)
                .build();

        Comment createdComment = commentRepository.save(comment);
        return commentMapper.toDto(createdComment);
    }

    //update
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment comment = validateId(id);

        if (commentDto.getCommentDetails() != null) {
            comment.updateCommentDetails(commentDto.getCommentDetails());
        }

        if (commentDto.getScheduleDto() != null) {
            Schedule schedule = scheduleMapper.toEntity(
                    scheduleService.findScheduleById(commentDto.getScheduleDto().getId())
            );
            comment.updateSchedule(schedule);
        }

        // JPA는 영속성 컨텍스트 내에서 엔티티의 변경 사항을 자동으로 추적하고 저장
        return commentMapper.toDto(comment);
    }

    //조회
    @Transactional(readOnly = true)
    public CommentDto getCommentById(Long id) {
        Comment comment = validateId(id);
        return commentMapper.toDto(comment);
    }

    //삭제
    @Transactional
    public void deleteCommentById(Long id) {
        validateId(id);
        commentRepository.deleteById(id);
    }

    private Comment validateId(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 comment을 찾을 수 없습니다."));

        return comment;
    }
}
