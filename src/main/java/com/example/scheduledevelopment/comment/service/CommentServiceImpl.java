package com.example.scheduledevelopment.comment.service;

import com.example.scheduledevelopment.comment.dto.CommentDto;
import com.example.scheduledevelopment.comment.entity.Comment;
import com.example.scheduledevelopment.comment.mapper.CommentMapper;
import com.example.scheduledevelopment.comment.repositroy.CommentRepository;
import com.example.scheduledevelopment.member.dto.MemberDto;
import com.example.scheduledevelopment.member.mapper.MemberMapper;
import com.example.scheduledevelopment.member.service.MemberServiceImpl;
import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import com.example.scheduledevelopment.schedule.mapper.ScheduleMapper;
import com.example.scheduledevelopment.schedule.service.ScheduleServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleServiceImpl scheduleServiceImpl;
    private final MemberServiceImpl memberServiceImpl;
    private final MemberMapper memberMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,
                              ScheduleMapper scheduleMapper, ScheduleServiceImpl scheduleServiceImpl, MemberServiceImpl memberServiceImpl, MemberMapper memberMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.scheduleMapper = scheduleMapper;
        this.scheduleServiceImpl = scheduleServiceImpl;
        this.memberServiceImpl = memberServiceImpl;
        this.memberMapper = memberMapper;
    }

    //create
    @Override
    @Transactional
    public CommentDto createComment(CommentDto commentDto) {

        MemberDto member = commentDto.getMemberDto() != null ?
                memberServiceImpl.findMemberById(commentDto.getMemberDto().getId()) :
                null;

        ScheduleDto schedule = commentDto.getScheduleDto() != null
                ? scheduleServiceImpl.findScheduleById(commentDto.getScheduleDto().getId())
                : null;

        Comment comment = Comment.builder()
                .commentDetails(commentDto.getCommentDetails())
                .schedule(scheduleMapper.toEntity(schedule))
                .member(memberMapper.toEntity(member))
                .build();

        Comment createdComment = commentRepository.save(comment);
        return commentMapper.toDto(createdComment);
    }

    //update
    @Override
    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment comment = validateId(id);

        if (commentDto.getCommentDetails() != null) {
            comment.updateCommentDetails(commentDto.getCommentDetails());
        }

        if (commentDto.getScheduleDto() != null) {
            Schedule schedule = scheduleMapper.toEntity(
                    scheduleServiceImpl.findScheduleById(commentDto.getScheduleDto().getId())
            );
            comment.updateSchedule(schedule);
        }

        // JPA는 영속성 컨텍스트 내에서 엔티티의 변경 사항을 자동으로 추적하고 저장
        return commentMapper.toDto(comment);
    }

    //조회
    @Override
    @Transactional(readOnly = true)
    public CommentDto getCommentById(Long id) {
        Comment comment = validateId(id);
        return commentMapper.toDto(comment);
    }

    //삭제
    @Override
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

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }
}
