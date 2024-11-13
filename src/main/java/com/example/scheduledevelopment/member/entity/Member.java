package com.example.scheduledevelopment.member.entity;

import com.example.scheduledevelopment.comment.entity.Comment;
import com.example.scheduledevelopment.schedule.entity.Schedule;
import com.example.scheduledevelopment.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String name;
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    /**
     * 메서드를 개별적으로 분리한이유
     * 명확한 개별적 책임 부여
     * 비즈니스 로직 반영 - 검증이나 수정할때 유지 보수성에서 이득
     */
    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
