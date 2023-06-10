package com.example.springboard.dto.user;

import com.example.springboard.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto(
        Long id,
        String userId,
        String userPassword,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy

) {
    public static UserAccountDto of(Long id,
                                    String userId,
                                    String userPassword,
                                    String email,
                                    String nickname,
                                    String memo,
                                    LocalDateTime createdAt,
                                    String createdBy,
                                    LocalDateTime updatedAt,
                                    String updatedBy) {
        return new UserAccountDto(id,
                userId,
                userPassword,
                email,
                nickname,
                memo,
                createdAt,
                createdBy,
                updatedAt,
                updatedBy);
    }

    public static UserAccountDto of(String userId,
                                    String userPassword,
                                    String email,
                                    String nickname,
                                    String memo) {
        return new UserAccountDto(null,
                userId,
                userPassword,
                email,
                nickname,
                memo,
                null,
                null,
                null,
                null);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                memo
        );
    }
}
