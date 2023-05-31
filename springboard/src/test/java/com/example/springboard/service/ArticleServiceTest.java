package com.example.springboard.service;

import com.example.springboard.domain.type.SearchType;
import com.example.springboard.dto.ArticleDto;
import com.example.springboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("[Service] 게시판 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;


    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");

        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 id가 주어지면 게시물을 반환한다")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        ArticleDto article = sut.searchArticle(1L);
        assertThat(article).isNotNull();
    }
}