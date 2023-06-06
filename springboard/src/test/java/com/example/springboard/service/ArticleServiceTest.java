package com.example.springboard.service;

import com.example.springboard.domain.Article;
import com.example.springboard.domain.type.SearchType;
import com.example.springboard.dto.ArticleDto;
import com.example.springboard.dto.UpdateArticleDto;
import com.example.springboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("[Service] 게시판 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;


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

    @DisplayName("게시글 정보를 주어지면 게시글을 저장한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveSuccess() {
        // given
        ArticleDto dto = ArticleDto.of(
                LocalDateTime.now(),
                "pleed",
                "title",
                "content",
                "#hashtag");

        given(articleRepository.save(any(Article.class))).willReturn(any(Article.class));

        // when
        sut.saveArticle(dto);

        // then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글을 수정할 수 있다.")
    @Test
    void givenArticleInfo_whenUpdatingArticle_thenUpateSuccess() {
        // given
        UpdateArticleDto dto = UpdateArticleDto.of(
                1L, "title", "content", "hashtag"
        );

        given(articleRepository.save(any(Article.class))).willReturn(any(Article.class));

        // when
        sut.updateArticle(dto);

        // then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글을 삭제할 수 있다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletingSuccess() {
        // given
        willDoNothing().given(articleRepository).deleteById(any(Long.class));

        // when
        sut.deleteArticle(1L);

        // then
        then(articleRepository).should().deleteById(any(Long.class));
    }
}