package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Articles;
import com.example.demokankretnalast.services.ArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticlesService articlesService;

    @GetMapping("/articles")
    public String art(@RequestParam(name = "title", required = false) String title, Model model){
        model.addAttribute("art", articlesService.listArticles(title));
        return "articlesPage/articles";
    }

    @GetMapping("/articles/{id}")
    public String tourInfo(@PathVariable Long id, Model model){
        model.addAttribute("tour", articlesService.getArticleById(id));
        return "articlesPage/article-more";
    }
    @PostMapping("/articles/add")
    public String addArticle(Articles articles, Model model){
        articlesService.saveArticle(articles);
        return "redirect:/articles";
    }
    @PostMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id){
        articlesService.deleteArticle(id);
        return "redirect:/articles";
    }
}
