package com.example.springboard.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article;
    private Long articleId;

    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
