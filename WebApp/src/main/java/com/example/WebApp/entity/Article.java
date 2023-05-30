package com.example.WebApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Article {
    @Id
    @GeneratedValue
    public UUID uuid;
    public String title;
    public UUID doctorUuid;
    @Column(columnDefinition = "LONGTEXT")
    public String description;
    @Column(columnDefinition = "LONGTEXT")
    public String content;
    public LocalDate writeDate;
    public LocalDate lastEditDate;
    public Integer likes;
}
