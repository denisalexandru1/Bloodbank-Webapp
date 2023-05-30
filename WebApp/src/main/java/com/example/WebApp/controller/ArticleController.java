package com.example.WebApp.controller;

import com.example.WebApp.dto.ArticleDTO;
import com.example.WebApp.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO createdArticle = articleService.createArticle(articleDTO);
        return ResponseEntity.ok(createdArticle);
    }

    @GetMapping("/article")
    ResponseEntity<List<ArticleDTO>> getAllArticles(){
        List<ArticleDTO> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/article/{uuid}")
    ResponseEntity<ArticleDTO> getArticleById(@PathVariable("uuid") UUID uuid){
        ArticleDTO article = articleService.getArticleById(uuid);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/article/doctor/{uuid}")
    ResponseEntity<List<ArticleDTO>> getArticlesByDoctorId(@PathVariable("uuid") UUID uuid){
        List<ArticleDTO> articles = articleService.getArticlesByDoctorId(uuid);
        return ResponseEntity.ok(articles);
    }

    @PutMapping("/article/{uuid}")
    ResponseEntity<ArticleDTO> updateArticle(@PathVariable("uuid") UUID uuid, @RequestBody ArticleDTO articleDTO){
        ArticleDTO updatedArticle = articleService.updateArticle(uuid, articleDTO);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/article/{uuid}")
    ResponseEntity<Void> deleteArticle(@PathVariable("uuid") UUID uuid){
        articleService.deleteArticle(uuid);
        return ResponseEntity.ok().build();
    }
}
