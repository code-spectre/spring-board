package com.example.springboard.dto.comment;

import com.example.springboard.domain.ArticleComment;
import com.example.springboard.dto.user.UserAccountDto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        UserAccountDto userAccount,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy
) {
    public static ArticleCommentDto of(Long id, UserAccountDto userAccount, String content, LocalDateTime createdAt,
                                       String createdBy,
                                       LocalDateTime updatedAt, String updatedBy) {
        return new ArticleCommentDto(id, userAccount, content, createdAt, createdBy, updatedAt, updatedBy);
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy()
        );
    }
}
