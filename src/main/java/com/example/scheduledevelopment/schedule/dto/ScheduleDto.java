package com.example.scheduledevelopment.schedule.dto;

import com.example.scheduledevelopment.comment.dto.CommentDto;
import com.example.scheduledevelopment.member.dto.MemberDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<CommentDto> commentDtoList = new ArrayList<>();

}
