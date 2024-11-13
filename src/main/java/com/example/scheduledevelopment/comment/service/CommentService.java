package com.example.scheduledevelopment.comment.service;

import com.example.scheduledevelopment.comment.dto.CommentDto;

//인터페이스만 바라봐도 되는것
//다른 구현체를 만든다 하더라도 만들어서 interface로 만들어 놓으면 바꿀 필요 없음
public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    CommentDto updateComment(Long id, CommentDto commentDto);
    CommentDto getCommentById(Long id);
    void deleteCommentById(Long id);
}
