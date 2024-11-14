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
public class ScheduleDto {
    private Long id;

    @NotBlank(message = "작성자는 필수 항목 입니다.")
    private String author;

    @NotBlank(message = "작성자는 필수 항목 입니다.")
    private String title;

    @NotBlank(message = "작성자는 필수 항목 입니다.")
    private String todo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberDto memberDto;
    private Long commentCount;

    public ScheduleDto(Long id, String author, String title, String todo, LocalDateTime createdAt, LocalDateTime updatedAt, Long commentCount) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.todo = todo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentCount = commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

}
