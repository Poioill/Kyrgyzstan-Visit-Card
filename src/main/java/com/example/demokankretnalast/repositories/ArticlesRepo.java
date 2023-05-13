package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticlesRepo extends JpaRepository<Articles, Long> {
    List<Articles> findArticlesByTitleContainingIgnoreCase(String title);
}
