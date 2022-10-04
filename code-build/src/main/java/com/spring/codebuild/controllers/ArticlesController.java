package com.spring.codebuild.controllers;

import com.spring.codebuild.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class ArticlesController {

    private ArticleService articleService;

    @Autowired
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article")
    public ResponseEntity getArticle(@RequestParam("id") int id) {
        ResponseEntity article = articleService.showArticle(id);

        return article;
    }

    @GetMapping("/articles")
    public ResponseEntity getArticles(@RequestParam("tag") String tag) {
        return ResponseEntity.ok(articleService.showArticles(tag));
    }

    @GetMapping("/article/tags")
    public ResponseEntity getArtcileTags() {
        return ResponseEntity.ok(articleService.getTags());
    }

    @GetMapping("/article/popular")
    public ResponseEntity getPopular(@RequestParam("id") int id) {
        return ResponseEntity.ok(articleService.getPopularArticles(id));
    }

    @PutMapping("/article/add_view")
    public ResponseEntity addView(@RequestBody int id) {
        String result = articleService.addViews(id);

        return ResponseEntity.ok(result);
    }
}
