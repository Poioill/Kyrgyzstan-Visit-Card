package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.Articles;
import com.example.demokankretnalast.repositories.ArticlesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticlesService {
    private final ArticlesRepo articlesRepo;

    public List<Articles> listArticles(String title) {
        if (title != null) return articlesRepo.findArticlesByTitleContainingIgnoreCase(title);
        return articlesRepo.findAll();
    }

    public void saveArticle(Articles articles) {
        log.info("Saving new {}", articles);
        articlesRepo.save(articles);
    }
    public void deleteArticle(Long id){
        articlesRepo.deleteById(id);
    }

    public Articles getArticleById(Long id) {
        return articlesRepo.findById(id).orElseThrow(null);
    }

    
}
