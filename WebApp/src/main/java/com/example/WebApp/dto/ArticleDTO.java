package com.example.WebApp.dto;

import java.time.LocalDate;
import java.util.UUID;

public class ArticleDTO {
    public UUID uuid;
    public String title;
    public String description;
    public String content;
    public LocalDate writeDate;
    public LocalDate lastEditDate;
    public Integer likes;
    public UUID doctorUuid;
}
