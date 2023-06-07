package com.example.springboard.controller;

import com.example.springboard.domain.Article;
import com.example.springboard.dto.article.ArticleDto;
import com.example.springboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping
    public String articles(ModelMap map) {
        map.addAttribute("articles", List.of());
        Page<ArticleDto> articles = articleService.searchArticles(null, null, Pageable.unpaged());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        map.addAttribute("article",  "article");
        map.addAttribute("articleComments",  List.of());
        return "articles/detail";
    }
}

