package com.example.scheduledevelopment.comment.entity;

import com.example.scheduledevelopment.member.entity.Member;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import com.example.scheduledevelopment.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    public void updateCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    public void updateSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
