package com.example.springboard.service;

import com.example.springboard.dto.comment.ArticleCommentDto;
import com.example.springboard.dto.user.UserAccountDto;
import com.example.springboard.repository.ArticleCommentRepository;
import com.example.springboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleCommentService {
    private final ArticleCommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException())
                .getArticleComments()
                .stream().map(comment -> ArticleCommentDto.of(
                        comment.getId(),
                        UserAccountDto.from(comment.getUserAccount()),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getCreatedBy(),
                        comment.getUpdatedAt(),
                        comment.getUpdatedBy()
                ))
                .toList();
    }
}
