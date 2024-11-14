package com.example.scheduledevelopment.schedule.mapper;

import com.example.scheduledevelopment.comment.mapper.CommentMapper;
import com.example.scheduledevelopment.member.mapper.MemberMapper;
import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import com.example.scheduledevelopment.schedule.dto.ScheduleResponseDto;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//처음에 생성자를 적용해보고 불편하다면 -> Mapper든 다른 방법으로 간다.
//Mapstruct를 써야 하는 이유 vs 생성자를 써야 하는 이유
@Mapper(componentModel = "spring", uses = {MemberMapper.class, CommentMapper.class})
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "member", target = "memberDto")
// Entity -> Dto
    ScheduleDto toDto(Schedule schedule);


    @Mapping(source = "memberDto", target = "member")
        // Dto -> Entity
    Schedule toEntity(ScheduleDto scheduleDto);

    @Mapping(source = "memberDto.id", target = "memberId")
    ScheduleResponseDto toResponseDto(ScheduleDto scheduleDto);
}