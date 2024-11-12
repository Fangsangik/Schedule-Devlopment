package com.example.scheduledevelopment.member.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;

    @NotBlank(message = "비밀번호는 필수 입니다.")
    @Size(min = 4, max = 8, message = "비밀번호는 4 ~ 8자 내외여야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 항목 입니다.")
    private String name;

    @NotBlank(message = "이름은 필수 항목 입니다.")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberDto withPassword(String password) {
        return MemberDto.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .password(password)
                .build();
    }
}
