package com.example.demokankretnalast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demokankretnalast.entity.Img;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageRepo extends JpaRepository<Img, Long> {
}
