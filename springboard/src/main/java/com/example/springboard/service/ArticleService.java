package com.example.springboard.service;

import com.example.springboard.domain.Article;
import com.example.springboard.domain.type.SearchType;
import com.example.springboard.dto.ArticleDto;
import com.example.springboard.dto.UpdateArticleDto;
import com.example.springboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword) {
        return Page.empty();
    }

    public ArticleDto searchArticle(Long id) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(Article.of(dto));
    }

    public void updateArticle(UpdateArticleDto dto) {
        articleRepository.save(Article.of(dto));
    }

    public void deleteArticle(long id) {
        articleRepository.deleteById(id);
    }
}
