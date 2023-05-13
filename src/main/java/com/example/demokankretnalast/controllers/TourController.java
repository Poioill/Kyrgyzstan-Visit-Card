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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        Tour tour = tourService.getTourById(id);
        model.addAttribute("tour", tour);
        model.addAttribute("images", tour.getImages());
        return "tour-info";
    }
    @PostMapping("/tours/add")
    public String addTour(@RequestParam("file1") MultipartFile file1,
                          @RequestParam("file2") MultipartFile file2,
                          @RequestParam("file3") MultipartFile file3,
                          Tour tour) throws IOException {
        tourService.saveTour(tour, file1, file2, file3);
        return "redirect:/tours";
    }
    @PostMapping("/tours/delete/{id}")
    public String deleteTour(@PathVariable Long id){
        tourService.deleteTour(id);
        return "redirect:/tours";
    }
}
