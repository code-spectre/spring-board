package controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 모든 controller를 테스트 할 필요는 없으므로
@WebMvcTest(ArticleControllerTest.class)
@DisplayName("View 컨트롤러 - 게시글")
class ArticleControllerTest {
    private final MockMvc mvc;

    // spring 코드에서는 꼭 autowired를 안해도 되지만 testing 코드에서는 반드시 autowired를 해서 의존성 주입을 해줘야 한다.
    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view] GET 게시글 리스트 호출 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        // given
        // when
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles"));
        // then
    }
    @DisplayName("[view] GET 게시글 단 건 호출 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        // given
        // when
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("article"));
        // then
    }

    @DisplayName("[view] GET - 게시글 검색 뷰 페이지 호출 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingSearchView_thenReturnsSearchView() throws Exception {
        // given
        // when
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
        // then
    }

    @DisplayName("[view] GET - 게시글 해시태그 뷰호출 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingHashtagView_thenReturnsHashtagView() throws Exception {
        // given
        // when
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
        // then
    }
}