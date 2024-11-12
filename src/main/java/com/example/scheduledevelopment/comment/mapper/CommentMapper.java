package com.example.scheduledevelopment.comment.mapper;

import com.example.scheduledevelopment.comment.dto.CommentDto;
import com.example.scheduledevelopment.comment.entity.Comment;
import com.example.scheduledevelopment.schedule.mapper.ScheduleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "schedule", target = "scheduleDto")
    CommentDto toDto(Comment comment);

    @Mapping(source = "scheduleDto", target = "schedule")
    Comment toEntity(CommentDto commentDto);
}
