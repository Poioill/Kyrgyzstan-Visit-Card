package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.BookTour;
import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Tour;
import com.example.demokankretnalast.services.BookService;
import com.example.demokankretnalast.services.RegionService;
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
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;
    private final RegionService regionService;
    private final BookService bookService;

    @GetMapping("/tours")
    public String tours(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        Iterable<Regions> region = regionService.findAllRegions();
        model.addAttribute("region", region);
        model.addAttribute("user", tourService.getUserByPrincipal(principal));
        model.addAttribute("tour", tourService.listTours(title));
        return "tours";
    }

    @GetMapping("/tours/{id}")
    public String tourInfo(@PathVariable Long id, Model model) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("region", regionService.findAllRegions());
        model.addAttribute("tour", tour);
        model.addAttribute("images", tour.getImages());
        return "tour-info";
    }

    @PostMapping("/tours/add")
    public String addTour(@RequestParam("file1") MultipartFile file1,
                          @RequestParam("file2") MultipartFile file2,
                          @RequestParam("file3") MultipartFile file3,
                          Tour tour, Principal principal) throws IOException {
        tourService.saveTour(principal, tour, file1, file2, file3);
        return "redirect:/tours";
    }

    @PostMapping("/tours/delete/{id}")
    public String deleteTour(@PathVariable Long id) {
        Tour tour = tourService.getTourById(id);
        tourService.deleteTour(tour.getId());
        return "redirect:/tours";
    }

    @GetMapping("/tours/update/{id}")
    public String updateTour(@PathVariable Long id,
                             Model model) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("tour", tour);
        return "tour-update";
    }

    @PostMapping("/tours/update/{id}")
    public String updateTour(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3,
                             Tour tour, Principal principal) throws IOException {
        tourService.deleteImages(tour);
        tourService.saveTour(principal, tour, file1, file2, file3);
        return "redirect:/tours/{id}";
    }


    @PostMapping("/book")
    public String booking(BookTour bookTour){
        bookService.saveBooking(bookTour);
        return "redirect:/tours";
    }
}
