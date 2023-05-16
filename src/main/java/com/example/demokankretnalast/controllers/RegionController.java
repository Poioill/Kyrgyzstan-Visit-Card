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
        Regions regions = regionService.getRegionById(id);
        Iterable<Regions> region = regionService.findAllRegions();
        model.addAttribute("region", region);
        model.addAttribute("reg", regions);
        model.addAttribute("images", regions.getImages());
        return "regionsPage/region";
    }
}
