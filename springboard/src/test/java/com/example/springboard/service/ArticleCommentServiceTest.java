package com.example.springboard.service;

import com.example.springboard.dto.comment.ArticleCommentDto;
import com.example.springboard.repository.ArticleCommentRepository;
import com.example.springboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("[댓글] 댓글 테스트")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    @Mock
    private ArticleCommentRepository commentRepository;
    @Mock
    private ArticleRepository articleRepository;
    @InjectMocks
    private ArticleCommentService sut;

    @DisplayName("댓글 조회 테스트")
    @Test
    void givenArticleId_whenReadingComment_thenListingSuccess() {
        // given
        Long testId = 1L;
        given(articleRepository.findById(testId)).willReturn(any());

        // when
        List<ArticleCommentDto> comments = sut.searchArticleComments(testId);
        // then
        assertThat(comments).isNotNull();
        then(articleRepository).should().findById(testId);
    }

    @DisplayName("댓글 생성 테스트")
    @Test
    void givenData_whenSavingComment_thenSavingSuccess() {
        // given
        // when
        // then
    }
}