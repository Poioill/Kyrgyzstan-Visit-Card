package com.example.demokankretnalast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("title", "Hi");
        return "home";
    }
}
