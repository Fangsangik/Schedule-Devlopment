package com.example.scheduledevelopment.member.service;

import com.example.scheduledevelopment.member.dto.MemberDto;

/**
 * interface로 가져간 이유
 * IoC와 DI를 생각해보면 interface로 가져가는게 좋다고 생각이 들었다.
 * 하지만 작은 프로젝트의 경우 비용적인 면에서 불리하다고 생각이 들기도 한다.
 * 그럼에도 불구하고 항상 service.class 이렇게 가져가는 것 보다는 IoC를 지켜보고 싶어 이렇게
 * 적용해봤음
 */
public interface MemberService {
    MemberDto createMember(MemberDto memberDto);

    MemberDto updateMember(Long id, MemberDto memberDto);

    MemberDto findMemberById(Long id);
    void deleteMemberById(Long memberId);
}
