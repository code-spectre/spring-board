package com.example.springboard.dto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy
) {
    public static ArticleCommentDto of(String content, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        return new ArticleCommentDto(content, createdAt, createdBy, updatedAt, updatedBy);
    }
}
