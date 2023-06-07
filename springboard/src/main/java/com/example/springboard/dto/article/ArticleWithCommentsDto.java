package com.example.springboard.dto.article;

import com.example.springboard.domain.Article;
import com.example.springboard.dto.comment.ArticleCommentDto;
import com.example.springboard.dto.user.UserAccountDto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsDto(
        Long id,
        UserAccountDto userAccount,
        Set<ArticleCommentDto> articleCommentsDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy

) {
    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccount,
                                          Set<ArticleCommentDto> articleCommentsDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
       return new ArticleWithCommentsDto(id, userAccount, articleCommentsDto, title, content, hashtag, createdAt,
               createdBy, updatedAt, updatedBy);
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream().map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy()
        );
    }
}
