package com.example.springboard.domain;

import com.example.springboard.dto.ArticleDto;
import com.example.springboard.dto.UpdateArticleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(
        name = "article",
        indexes = {
                @Index(columnList = "title"),
                @Index(columnList = "hashtag"),
                @Index(columnList = "createdAt"),
                @Index(columnList = "createdBy"),
        })
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article extends AuditingFields {

    @Setter
    @Column(nullable = false, length = 255)
    private String title;
    @Setter
    @Column(nullable = false, length = 10000)
    private String content;
    @Setter
    @Column(nullable = true, length = 100)
    private String hashtag;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    protected Article() {
    }

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    public static Article of(ArticleDto dto) {
        return new Article(dto.title(), dto.content(), dto.hashtag());
    }

    public static Article of(UpdateArticleDto dto) {
        Article article = new Article(
                dto.title(),
                dto.content(),
                dto.hashtag()
        );
        article.id = dto.id();
        return article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}