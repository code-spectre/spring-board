package com.example.springboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@ToString
@Table(name = "user_account",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "email", unique = true),
                @Index(columnList = "createdAt"),
                @Index(columnList = "createdBy"),
        })
@Entity
public class UserAccount extends AuditingFields {
    @Setter
    @Column(nullable = false, length = 50)
    private String userId;
    @Setter
    @Column(nullable = false)
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;
    @Setter
    @Column(length = 100)
    private String nickname;
    @Setter
    @Column
    private String memo;

    protected UserAccount() {
    }

    private UserAccount(String userId, String password, String email, String nickname, String memo) {
        this.userId = userId;
        this.userPassword = password;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String userId, String password, String email, String nickname, String memo) {
        return new UserAccount(userId, password, email, nickname, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
