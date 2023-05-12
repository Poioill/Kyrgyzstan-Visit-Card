package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.Tour;
import com.example.demokankretnalast.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping("/tours")
    public String tours(@RequestParam(name = "title", required = false) String title, Model model){
        model.addAttribute("tour", tourService.listTours(title));
        return "tours";
    }

    @GetMapping("/tours/{id}")
    public String tourInfo(@PathVariable Long id, Model model){
        model.addAttribute("tour", tourService.getTourById(id));
        return "tour-info";
    }
    @PostMapping("/tours/add")
    public String addTour(Tour tour, Model model){
        tourService.saveTour(tour);
        return "redirect:/tours";
    }
    @PostMapping("/tours/delete/{id}")
    public String deleteTour(@PathVariable Long id){
        tourService.deleteTour(id);
        return "redirect:/tours";
    }
}
