package com.example.springboard.dto.article;

import com.example.springboard.domain.Article;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String nickname
) {
    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt,
                            String nickname) {
        return new ArticleResponse(id, title, content, hashtag, createdAt, nickname);
    }

    public static ArticleResponse from(ArticleDto article) {
        String nickname = article.userAccount().nickname();
        if(nickname == null || nickname.isBlank()) {
            nickname = article.userAccount().userId();
        }

        return new ArticleResponse(
                article.id(),
                article.title(),
                article.content(),
                article.hashtag(),
                article.createdAt(),
                nickname
        );
    }
}
