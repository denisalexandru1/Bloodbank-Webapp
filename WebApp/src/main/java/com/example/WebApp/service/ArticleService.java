package com.example.WebApp.service;

import com.example.WebApp.dto.ArticleDTO;
import com.example.WebApp.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ArticleService {
    ArticleDTO createArticle(ArticleDTO articleService);
    List<ArticleDTO> getAllArticles();
    ArticleDTO getArticleById(UUID uuid);
    List<ArticleDTO> getArticlesByDoctorId(UUID uuid);
    ArticleDTO updateArticle(UUID uuid, ArticleDTO articleDTO);
    void deleteArticle(UUID uuid);
    void deleteAllArticlesByDoctorUUID(UUID uuid);
}
