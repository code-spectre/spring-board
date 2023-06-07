package com.example.springboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(
        name="article_comment",
        indexes = {
        @Index(columnList="content"),
        @Index(columnList="createdAt"),
        @Index(columnList="createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment extends AuditingFields {

    @Setter @ManyToOne(optional=false) private Article article;

    @Setter @Column(nullable = false, length=500) private String content;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;


    protected ArticleComment() {
    }

    private ArticleComment(UserAccount userAccount, Article article, String content) {
        this.userAccount = userAccount;
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(UserAccount userAccount, Article article, String content) {
        return new ArticleComment(userAccount, article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
