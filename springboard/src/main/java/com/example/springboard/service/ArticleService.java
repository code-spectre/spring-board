package com.example.springboard.service;

import com.example.springboard.domain.Article;
import com.example.springboard.domain.type.SearchType;
import com.example.springboard.dto.article.ArticleDto;
import com.example.springboard.dto.article.ArticleWithCommentsDto;
import com.example.springboard.dto.article.UpdateArticleDto;
import com.example.springboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID ->
                    articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME ->
                    articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#"+searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(Long id) {
        return null;
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
//        return ArticleWithCommentsDto.from(articleRepository.findById(articleId).orElseThrow());
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(()->new EntityNotFoundException("Article not found: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(UpdateArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.id());
            if(dto.title() != null) { article.setTitle(dto.title()); }
            if(dto.content() != null) { article.setTitle(dto.content()); }
            if(dto.hashtag() != null) { article.setHashtag(dto.hashtag());}
            articleRepository.save(article);
        } catch(EntityNotFoundException e) {
            log.warn("Updating failed: Not found entity {}", dto);
        }
    }

    public void deleteArticle(long id) {
        articleRepository.deleteById(id);
    }
}
