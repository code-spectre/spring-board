package com.example.springboard.controller;

import com.example.springboard.config.SecurityConfig;
import com.example.springboard.domain.type.SearchType;
import com.example.springboard.service.ArticleService;
import com.example.springboard.service.PaginationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 모든 controller를 테스트 할 필요는 없으므로
@WebMvcTest(ArticleController.class)
@Import({SecurityConfig.class})
@DisplayName("View 컨트롤러 - 게시글")
class ArticleControllerTest {
    private final MockMvc mvc;
    @MockBean
    private ArticleService articleService;

    @MockBean
    private PaginationService paginationService;

    // spring 코드에서는 꼭 autowired를 안해도 되지만 testing 코드에서는 반드시 autowired를 해서 의존성 주입을 해줘야 한다.
    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[view] GET 게시글 리스트 호출 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        // given
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class)))
                .willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of());
        // when
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("paginationBarNumbers"));
        // then
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }


    @DisplayName("[view] GET 게시글 단 건 호출 - 정상 호출")
    @Test
    public void givenArticleId_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        // given

        // when
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));
        // then
    }


    @DisplayName("[view] GET - 게시글 검색 뷰 페이지 호출 - 정상 호출")
    @Test
    public void givenSearchKeyword_whenRequestingSearching_thenReturnsMatchingArticles() throws Exception {
        // given
        SearchType searchType = SearchType.TITLE;
        String searchTerm = "title";

        given(articleService.searchArticles(eq(searchType), eq(searchTerm), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of());

        // when
        mvc.perform(get("/articles")
                        .queryParam("searchType", searchType.name())
                        .queryParam("searchTerm", searchTerm)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("searchTypes"));
        // then
        then(articleService).should().searchArticles(eq(searchType), eq(searchTerm), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[view] GET - 게시글 해시태그 뷰호출 - 정상 호출")
    @Test
    public void givenHashtag_whenRequestingHashtagView_thenReturnsHashtagView() throws Exception {
        // given
        String hashtag = "java";
        given(articleService.searchArticlesByHashtag(eq(hashtag), any(Pageable.class))).willReturn(Page.empty());
        // when
        mvc.perform(get("/articles/search-hashtag").queryParam("hashtag", hashtag))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search-hashtag"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("hashtags"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andExpect(model().attributeExists("searchTypes"));
        // then
        then(articleService).should().searchArticlesByHashtag(eq(hashtag), any(Pageable.class));
    }
}