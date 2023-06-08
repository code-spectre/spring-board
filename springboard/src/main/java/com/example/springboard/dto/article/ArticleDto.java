package com.example.springboard.dto.article;

import com.example.springboard.domain.Article;
import com.example.springboard.dto.user.UserAccountDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccount,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        String title,
        String content,
        String hashtag
) implements Serializable {
    public static ArticleDto of(Long id, UserAccountDto userAccount, LocalDateTime createdAt, String createdBy,
                                LocalDateTime updatedAt,
                                String updatedBy, String title, String content,
                                String hashtag) {
        return new ArticleDto(id, userAccount, createdAt, createdBy, updatedAt, updatedBy, title, content, hashtag);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(
                article.getId(),
                UserAccountDto.from(article.getUserAccount()),
                article.getCreatedAt(),
                article.getCreatedBy(),
                article.getUpdatedAt(),
                article.getUpdatedBy(),
                article.getTitle(),
                article.getContent(),
                article.getHashtag()
        );
    }

    public Article toEntity() {
        return Article.of(
                userAccount.toEntity(),
                title,
                content,
                hashtag
        );
    }
}
