package com.example.scheduledevelopment.member.mapper;

import com.example.scheduledevelopment.member.entity.Member;
import com.example.scheduledevelopment.member.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct 사용한 이유
 * DTO와 엔티티간 매핑 코드를 자동으로 생성, 코드량 줄어들고 개발 생산성이 높아진다고 생각
 * 일관성, 수동으로 작성한 코드에서의 실수 감소 할 수 있지 않을까 싶었다.
 */
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
