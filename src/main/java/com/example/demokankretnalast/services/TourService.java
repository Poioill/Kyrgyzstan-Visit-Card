package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.Tour;
import com.example.demokankretnalast.repositories.TourRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TourService {
    private final TourRepo tourRepo;

    public List<Tour> listTours(String title) {
        if (title != null) return tourRepo.findByTitleContainingIgnoreCase(title);
        return tourRepo.findAll();
    }

    public void saveTour(Tour tour) {
//        Tour tourNew = tourRepo.save(tour);
        log.info("Saving new {}", tour);
        tourRepo.save(tour);
    }
    public void deleteTour(Long id){
        tourRepo.deleteById(id);
    }

    public Tour getTourById(Long id) {
        return tourRepo.findById(id).orElseThrow(null);
    }

}
