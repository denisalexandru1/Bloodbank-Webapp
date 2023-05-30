package com.example.WebApp.service.impl;

import com.example.WebApp.dto.ArticleDTO;
import com.example.WebApp.entity.Article;
import com.example.WebApp.mapper.ArticleMapper;
import com.example.WebApp.repository.ArticleRepository;
import com.example.WebApp.service.ArticleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article createdArticle = articleRepository.save(articleMapper.toArticle(articleDTO));
        return articleMapper.toDTO(createdArticle);
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(articleMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getArticleById(UUID uuid) {
        Optional<Article> article = articleRepository.findById(uuid);
        if(article.isPresent()){
            return articleMapper.toDTO(article.get());
        }
        else {
            throw new InvalidParameterException("Article with id " + uuid + " does not exist");
        }
    }

    @Override
    public List<ArticleDTO> getArticlesByDoctorId(UUID uuid) {
        List<Article> articles = articleRepository.findAllByDoctorUuid(uuid);
        return articles.stream().map(articleMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ArticleDTO updateArticle(UUID uuid, ArticleDTO articleDTO) {
        Optional<Article> article = articleRepository.findById(uuid);
        if(article.isPresent()){
            Article updatedArticle = articleRepository.save(articleMapper.toArticle(articleDTO));
            return articleMapper.toDTO(updatedArticle);
        }
        else {
            throw new InvalidParameterException("Article with id " + uuid + " does not exist");
        }
    }

    @Override
    public void deleteArticle(UUID uuid) {
        articleRepository.deleteById(uuid);
    }

    @Override
    @Transactional
    public void deleteAllArticlesByDoctorUUID(UUID uuid) {
        articleRepository.deleteAllByDoctorUuid(uuid);
    }
}
