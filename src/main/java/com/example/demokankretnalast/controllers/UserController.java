package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.services.RegionService;
import com.example.demokankretnalast.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RegionService regionService;
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user")User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("region",regionService.findAllRegions());
        model.addAttribute("tours", user.getTours());
        return "user-info";
    }
}
