package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping
@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if (!userService.createUser(user)){
            model.addAttribute("message", "User already exists! Please ");
            return "registration";
        }
        return "redirect:/login";
    }
}
