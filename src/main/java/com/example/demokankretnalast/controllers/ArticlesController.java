package com.example.demokankretnalast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ArticlesController {
    @GetMapping("/articles")
    public String article(Model model){
        return "articlesPage/articles";
    }
    @GetMapping("/artic")
    public String artic(Model model){
        return "articlesPage/artic";
    }
}
