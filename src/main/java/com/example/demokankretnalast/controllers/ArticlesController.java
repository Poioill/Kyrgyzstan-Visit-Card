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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
        Articles articles = articlesService.getArticleById(id);
        model.addAttribute("art", articlesService.getArticleById(id));
        model.addAttribute("images", articles.getImages());
        return "articlesPage/article-more";
    }
    @PostMapping("/articles/add")
    public String addArticle(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3,
                             Articles articles) throws IOException {
        articlesService.saveArticle(articles, file1, file2, file3);
        return "redirect:/articles";
    }
    @PostMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id){
        articlesService.deleteArticle(id);
        return "redirect:/articles";
    }
}
