package com.example.scheduledevelopment.schedule.entity;

import com.example.scheduledevelopment.comment.entity.Comment;
import com.example.scheduledevelopment.member.entity.Member;
import com.example.scheduledevelopment.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public void updateAuthor(String author) {
        this.author = author;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateTodo(String todo) {
        this.todo = todo;
    }

    public void updateMember(Member member) {
        this.member = member;
    }
}
