package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Role;
import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.services.RegionService;
import com.example.demokankretnalast.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("region",regionService.findAllRegions());
        model.addAttribute("regions", regionService.findAllRegions());
        model.addAttribute("users", userService.list());
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

}
