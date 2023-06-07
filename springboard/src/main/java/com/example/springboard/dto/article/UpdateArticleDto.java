package com.example.springboard.dto.article;

public record UpdateArticleDto(
        Long id,
        String title,
        String content,
        String hashtag
) {
    public static UpdateArticleDto of(Long id, String title, String content, String hashtag) {
        return new UpdateArticleDto(id, title, content, hashtag);
    }
}
