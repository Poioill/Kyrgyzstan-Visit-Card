package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Articles;
import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Tour;
import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.services.ArticlesService;
import com.example.demokankretnalast.services.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticlesService articlesService;
    private final RegionService regionService;

    @GetMapping("/articles")
    public String art(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("art", articlesService.listArticles(title));
        Iterable<Regions> region = regionService.findAllRegions();
        model.addAttribute("region", region);
        return "articlesPage/articles";
    }

    @GetMapping("/articles/{id}")
    public String artInfo(@PathVariable Long id,
                          Principal principal,
                          Model model) {
        model.addAttribute("region", regionService.findAllRegions());
        model.addAttribute("art", articlesService.getArticleById(id));
        model.addAttribute("user", principal);
        return "articlesPage/article-more";
    }

    @PostMapping("/articles/add")
    public String addArticle(Principal principal,
                             @RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3,
                             Articles articles) throws IOException {
        articlesService.saveArticle(principal, articles, file1, file2, file3);
        return "redirect:/articles";
    }

    @GetMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        Articles articles = articlesService.getArticleById(id);
        model.addAttribute("images", articles.getImages());
        model.addAttribute("art", articles);
        return "articlesPage/art-update";
    }

    @PostMapping("/articles/update/{id}")
    public String updateArticle(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3,
                             Articles articles, Principal principal) throws IOException {
        articlesService.saveArticle(principal, articles, file1, file2, file3);
        return "redirect:/articles/{id}";
    }

    @PostMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articlesService.deleteArticle(id);
        return "redirect:/articles";
    }
}
