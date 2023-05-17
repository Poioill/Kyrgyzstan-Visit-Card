package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.*;
import com.example.demokankretnalast.repositories.ImgRegionRepo;
import com.example.demokankretnalast.repositories.RegionRepo;
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
public class RegionService {
    private final RegionRepo regionRepo;
    private final UserRepo userRepo;
    private final ImgRegionRepo imgRegionRepo;

    public List<Regions> findAllRegions() {
        return regionRepo.findAll();
    }

    public void saveRegion(Principal principal,
                           Regions region,
                           MultipartFile fileMain,
                           MultipartFile fileIntro,
                           MultipartFile file1,
                           MultipartFile file2,
                           MultipartFile file3) throws IOException {
        region.setUser(getUserByPrincipal(principal));
        ImgRegion imgMain;
        ImgRegion imgIntro;
        ImgRegion img1;
        ImgRegion img2;
        ImgRegion img3;
        if (fileMain.getSize() != 0) {
            imgMain = toImageEntity(fileMain);
            region.addImageToRegion(imgMain);
        }
        if (fileIntro.getSize() != 0) {
            imgIntro = toImageEntity(fileIntro);
            region.addImageToRegion(imgIntro);
        }
        if (file1.getSize() != 0) {
            img1 = toImageEntity(file1);
            region.addImageToRegion(img1);
        }
        if (file2.getSize() != 0) {
            img2 = toImageEntity(file2);
            region.addImageToRegion(img2);
        }
        if (file3.getSize() != 0) {
            img3 = toImageEntity(file3);
            region.addImageToRegion(img3);
        }
        log.info("Saving new Region. RegionFullName: {}; Author email: {}", region.getRegionFullName(), region.getUser().getEmail());
        regionRepo.save(region);
    }

    private ImgRegion toImageEntity(MultipartFile file) throws IOException {
        ImgRegion img = new ImgRegion();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    public Regions getRegionById(Long id) {
        return regionRepo.findById(id).orElseThrow(null);
    }
}
