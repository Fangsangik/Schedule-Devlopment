package com.example.scheduledevelopment.comment.mapper;

import com.example.scheduledevelopment.comment.dto.CommentDto;
import com.example.scheduledevelopment.comment.dto.CommentResponseDto;
import com.example.scheduledevelopment.comment.entity.Comment;
import org.mapstruct.Mapper;


import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "schedule", target = "scheduleDto")
    @Mapping(source = "member", target = "memberDto")
    CommentDto toDto(Comment comment);

    @Mapping(source = "scheduleDto", target = "schedule")
    @Mapping(source = "memberDto", target = "member")
    Comment toEntity(CommentDto commentDto);

    @Mapping(source = "scheduleDto.id", target = "scheduleId")
    @Mapping(source = "memberDto.id", target = "memberId")
    CommentResponseDto toResponseDto(CommentDto commentDto);

}
