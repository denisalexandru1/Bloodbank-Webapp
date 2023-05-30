package com.example.WebApp.mapper;

import com.example.WebApp.dto.ArticleDTO;
import com.example.WebApp.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public Article toArticle(ArticleDTO articleDTO){
        Article article = new Article();
        article.uuid = articleDTO.uuid;
        article.title = articleDTO.title;
        article.content = articleDTO.content;
        article.description = articleDTO.description;
        article.writeDate = articleDTO.writeDate;
        article.lastEditDate = articleDTO.lastEditDate;
        article.likes = articleDTO.likes;
        article.doctorUuid = articleDTO.doctorUuid;
        return article;
    }

    public ArticleDTO toDTO(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.uuid = article.uuid;
        articleDTO.title = article.title;
        articleDTO.content = article.content;
        articleDTO.description = article.description;
        articleDTO.writeDate = article.writeDate;
        articleDTO.lastEditDate = article.lastEditDate;
        articleDTO.likes = article.likes;
        articleDTO.doctorUuid = article.doctorUuid;
        return articleDTO;
    }
}
