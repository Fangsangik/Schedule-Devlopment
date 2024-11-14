package com.example.scheduledevelopment.comment.controller;

import com.example.scheduledevelopment.comment.dto.CommentDto;
import com.example.scheduledevelopment.comment.dto.CommentResponseDto;
import com.example.scheduledevelopment.comment.mapper.CommentMapper;
import com.example.scheduledevelopment.comment.service.CommentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;
    private final CommentMapper commentMapper;

    public CommentController(CommentServiceImpl commentServiceImpl, CommentMapper commentMapper) {
        this.commentServiceImpl = commentServiceImpl;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> findById(
            @PathVariable Long commentId) {
        try {
            CommentDto commentById = commentServiceImpl.getCommentById(commentId);
            CommentResponseDto responseDto = commentMapper.toResponseDto(commentById);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("comments를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(
            @Valid @RequestBody CommentDto commentDto) {
        try {
            CommentDto create = commentServiceImpl.createComment(commentDto);
            CommentResponseDto responseDto = commentMapper.toResponseDto(create);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("생성에 실패했습니다.");
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> update(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentDto commentDto) {

        try {
            CommentDto comment = commentServiceImpl.updateComment(commentId, commentDto);
            CommentResponseDto responseDto = commentMapper.toResponseDto(comment);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("update 실패했습니다.");
        }
    }

    @DeleteMapping("/{deleteById}")
    public ResponseEntity<?> delete(@PathVariable Long deleteById) {

        try {
            commentServiceImpl.deleteCommentById(deleteById);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("삭제에 실패했습니다.");
        }
    }
}
