package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.services.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;
    @GetMapping("/regions/{id}")
    public String getRegion(@PathVariable(value = "id") Long id, Model model){
        Regions region = regionService.getRegionById(id);
        Iterable<Regions> regions = regionService.findAllRegions();
        model.addAttribute("region", regions);
        model.addAttribute("reg", region);
        model.addAttribute("images", region.getImagesRegion());
        return "regionsPage/region";
    }
}
