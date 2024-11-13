package com.example.scheduledevelopment.schedule.mapper;

import com.example.scheduledevelopment.comment.mapper.CommentMapper;
import com.example.scheduledevelopment.member.mapper.MemberMapper;
import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, CommentMapper.class})
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "member", target = "memberDto")
// Entity -> Dto
    ScheduleDto toDto(Schedule schedule);


    @Mapping(source = "memberDto", target = "member")
        // Dto -> Entity
    Schedule toEntity(ScheduleDto scheduleDto);
}