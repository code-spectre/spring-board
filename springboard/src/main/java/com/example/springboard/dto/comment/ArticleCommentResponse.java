package com.example.springboard.dto.comment;

import com.example.springboard.repository.ArticleCommentRepository;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickname
) {
    public static ArticleCommentResponse of (Long id, String content, LocalDateTime createdAt, String email,
                                       String nickname, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleCommentResponse(id, content, createdAt, email, nickname);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        String nickname = dto.userAccount().nickname();
        if(nickname == null || nickname.isBlank()) {
            nickname = dto.userAccount().userId();
        }
        return new ArticleCommentResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.userAccount().email(),
                nickname
        );
    }
}
