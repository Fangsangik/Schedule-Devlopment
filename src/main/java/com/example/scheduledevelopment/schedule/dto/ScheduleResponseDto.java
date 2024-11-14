package com.example.scheduledevelopment.schedule.dto;

import com.example.scheduledevelopment.member.dto.MemberDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDto {
    private Long id;
    private String author;
    private String title;
    private String todo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long memberId;
    private Long commentCount;

}
