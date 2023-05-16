package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.Img;
import com.example.demokankretnalast.entity.Tour;
import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.repositories.ImageRepo;
import com.example.demokankretnalast.repositories.TourRepo;
import com.example.demokankretnalast.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TourService {
    private final TourRepo tourRepo;
    private final UserRepo userRepo;
    private final ImageRepo imageRepo;

    public List<Tour> listTours(String title) {
        if (title != null) return tourRepo.findByTitleContainingIgnoreCase(title);
        return tourRepo.findAll();
    }

    public void saveTour(Principal principal, Tour tour, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        tour.setUser(getUserByPrincipal(principal));
        Img img1;
        Img img2;
        Img img3;
        if (file1.getSize() != 0){
            img1 = toImageEntity(file1);
            img1.setPreviewImage(true);
            tour.addImageToProduct(img1);
        }
        if (file2.getSize() != 0){
            img2 = toImageEntity(file2);
            tour.addImageToProduct(img2);
        }
        if (file3.getSize() != 0){
            img3 = toImageEntity(file3);
            tour.addImageToProduct(img3);
        }
        log.info("Saving new Tour. Title: {}; Author email: {}", tour.getTitle(), tour.getUser().getEmail());
        Tour tourFromDB = tourRepo.save(tour);
        tourFromDB.setPreviewImageId(tourFromDB.getImages().get(0).getId());
        tourRepo.save(tour);
    }

    public void saveTourWithoutPic(Principal principal, Tour tour){
        tour.setUser(getUserByPrincipal(principal));
        log.info("Saving new Tour. Title: {}; Author email: {}; ", tour.getTitle(), tour.getUser().getEmail());
        Tour tourFromDB = tourRepo.save(tour);
        tourRepo.save(tour);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    private Img toImageEntity(MultipartFile file) throws IOException{
        Img img = new Img();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }
    public void deleteTour(Long id){
        tourRepo.deleteById(id);
    }

    public Tour getTourById(Long id) {
        return tourRepo.findById(id).orElseThrow(null);
    }

}
