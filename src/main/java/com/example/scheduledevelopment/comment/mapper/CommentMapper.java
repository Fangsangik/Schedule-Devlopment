package com.example.scheduledevelopment.comment.mapper;

import com.example.scheduledevelopment.comment.dto.CommentDto;
import com.example.scheduledevelopment.comment.entity.Comment;
import org.mapstruct.Mapper;


import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "schedule", target = "scheduleDto")
    @Mapping(source = "member", target = "MemberDto")
    CommentDto toDto(Comment comment);

    @Mapping(source = "scheduleDto", target = "schedule")
    @Mapping(source = "memberDto", target = "member")
    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> comments);

    List<Comment> toEntityList(List<CommentDto> commentDtos);
}
