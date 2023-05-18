package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.services.RegionService;
import com.example.demokankretnalast.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final RegionService regionService;
    private final UserService userService;

    @GetMapping("/")
    public String homePage(Model model){
        Iterable<Regions> region = regionService.findAllRegions();
        model.addAttribute("region", region);
        return "home";
    }
}
