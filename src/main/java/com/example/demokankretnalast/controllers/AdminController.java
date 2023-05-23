package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Role;
import com.example.demokankretnalast.entity.Sightseeing;
import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.repositories.TourRepo;
import com.example.demokankretnalast.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RegionService regionService;
    private final BookService bookService;
    private final TourRepo tourRepo;
    private final SightseeingService sightseeingService;

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("region",regionService.findAllRegions());
        model.addAttribute("users", userService.list());
        model.addAttribute("usr", user);
        model.addAttribute("tour", tourRepo.findAll());
        model.addAttribute("book", bookService.allBooking());
        return "admin";
    }

    @PostMapping("/admin/create/region")
    public String createRegion(Principal principal,
                               Regions regions,
                               @RequestParam("fileMain") MultipartFile fileMain,
                               @RequestParam("fileIntro") MultipartFile fileIntro,
                               @RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3,
                               Model model) throws IOException {
        regionService.saveRegion(principal, regions, fileMain, fileIntro, file1, file2, file3);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id, Model model) {
        userService.banUser(id);
        model.addAttribute("region",regionService.findAllRegions());
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }

    @PostMapping("/admin/create/sightseeing")
    public String createAttraction(@ModelAttribute("region") Sightseeing sightseeing,
                                   @RequestParam("select") Long regionId) {
        sightseeingService.addSightseeing(sightseeing, regionId);
        return "redirect:/admin";
    }

}
