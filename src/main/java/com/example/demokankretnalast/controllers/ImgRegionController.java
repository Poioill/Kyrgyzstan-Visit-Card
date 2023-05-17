package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.ImgRegion;
import com.example.demokankretnalast.repositories.ImgRegionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImgRegionController {
    private final ImgRegionRepo imgRegionRepo;

    @GetMapping("/imagesRegion/{id}")
    private ResponseEntity<?> getImageRegionById(@PathVariable Long id){
        ImgRegion img = imgRegionRepo.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", img.getOriginalFileName())
                .contentType(MediaType.valueOf(img.getContentType()))
                .contentLength(img.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(img.getBytes())));
    }
}
