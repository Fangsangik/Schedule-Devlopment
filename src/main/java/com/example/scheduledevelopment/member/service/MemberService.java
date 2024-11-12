package com.example.scheduledevelopment.member.service;

import com.example.scheduledevelopment.config.PasswordEncoder;
import com.example.scheduledevelopment.member.entity.Member;
import com.example.scheduledevelopment.member.mapper.MemberMapper;
import com.example.scheduledevelopment.member.dto.MemberDto;
import com.example.scheduledevelopment.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }


    //create
    @Transactional
    public MemberDto createMember(MemberDto memberDto) {
        memberDto = encryptedPassword(memberDto);

        Member member = memberMapper.toEntity(memberDto);
        Member createMember = memberRepository.save(member);
        return memberMapper.toDto(createMember);
    }

    //update
    @Transactional
    public MemberDto updateMember(Long id, MemberDto memberDto) {
        Member findMember = validateId(id);

        // 비밀번호 암호화 (필요한 경우)
        memberDto = encryptedPassword(memberDto);

        // 기존 엔티티의 필드 업데이트 (setter 사용 없이 필드를 직접 수정하는 방식)
        if (memberDto.getName() != null) {
            findMember.updateName(memberDto.getName()); // 엔티티 메서드로 필드 수정
        }
        if (memberDto.getEmail() != null) {
            findMember.updateEmail(memberDto.getEmail()); // 엔티티 메서드로 필드 수정
        }
        if (memberDto.getPassword() != null) {
            findMember.updatePassword(memberDto.getPassword()); // 엔티티 메서드로 필드 수정
        }

        // JPA는 영속성 컨텍스트 내에서 엔티티의 변경 사항을 자동으로 추적하고 저장
        return memberMapper.toDto(findMember);

    }

    //조회
    @Transactional(readOnly = true)
    public MemberDto findMemberById(Long id) {
        Member findMember = validateId(id);
        return memberMapper.toDto(findMember);
    }

    //삭제
    @Transactional
    public void deleteMemberById(Long memberId) {
        validateId(memberId);
        // 회원 삭제
        memberRepository.deleteById(memberId);
    }


    public boolean authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email);

        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
            return true;
        }

        return false;
    }


    private Member validateId(Long id) {
        Member findMemberId = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        return findMemberId;
    }

    private MemberDto encryptedPassword(MemberDto memberDto) {
        if (memberDto.getPassword() != null && !memberDto.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
            memberDto = memberDto.withPassword(encodedPassword);
        }
        return memberDto;
    }
}
