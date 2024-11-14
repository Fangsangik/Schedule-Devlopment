package com.example.scheduledevelopment.comment.dto;


import com.example.scheduledevelopment.schedule.dto.ScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String commentDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long memberId;
    private Long scheduleId;
}
