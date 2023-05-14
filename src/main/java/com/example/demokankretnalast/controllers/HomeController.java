package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.services.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final RegionService regionService;

    @GetMapping("/")
    public String homePage(Model model){
        Iterable<Regions> region = regionService.findAllRegions();
        model.addAttribute("title", "Hi");
        model.addAttribute("region", region);
        return "home";
    }
}
