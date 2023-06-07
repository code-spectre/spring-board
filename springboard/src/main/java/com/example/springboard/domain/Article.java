package com.example.springboard.domain;

import com.example.springboard.dto.article.ArticleDto;
import com.example.springboard.dto.article.UpdateArticleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
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
    @OrderBy("createdAt DESC")
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Setter
    @ManyToOne(optional = false)
    private UserAccount userAccount;


    protected Article() {
    }

    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
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