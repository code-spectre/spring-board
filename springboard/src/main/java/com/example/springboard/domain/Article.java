package com.example.springboard.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@ToString

public class Article {
    private Long id;
    private String title;
    private String content;
    private String hashtag;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
