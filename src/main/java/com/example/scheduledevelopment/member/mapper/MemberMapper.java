package com.example.scheduledevelopment.member.mapper;

import com.example.scheduledevelopment.member.entity.Member;
import com.example.scheduledevelopment.member.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mappings({
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt")
    })

    MemberDto toDto(Member member);

    Member toEntity(MemberDto memberDto);
}
