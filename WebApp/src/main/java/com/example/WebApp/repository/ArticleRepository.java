package com.example.WebApp.repository;

import com.example.WebApp.dto.ArticleDTO;
import com.example.WebApp.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
    List<Article> findAllByDoctorUuid(UUID uuid);

    void deleteAllByDoctorUuid(UUID uuid);
}
