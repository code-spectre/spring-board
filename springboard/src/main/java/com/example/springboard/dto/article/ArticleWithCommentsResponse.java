package com.example.springboard.dto.article;

import com.example.springboard.dto.comment.ArticleCommentResponse;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String nickname,
        String userId,
        Set<ArticleCommentResponse> articleCommentResponses
) {

    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtag,
                                        LocalDateTime createdAt, String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, title, content, hashtag, createdAt, email, nickname, userId,
                articleCommentResponses);
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccount().nickname();
        if(nickname == null || nickname.isBlank()) {
            nickname = dto.userAccount().userId();
        }

        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccount().email(),
                nickname,
                dto.userAccount().userId(),
                dto.articleCommentsDto().stream().map(
                        ArticleCommentResponse::from
                ).collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}


