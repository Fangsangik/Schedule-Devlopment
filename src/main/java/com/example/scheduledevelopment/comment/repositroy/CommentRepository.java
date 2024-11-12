package com.example.scheduledevelopment.comment.repositroy;

import com.example.scheduledevelopment.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
