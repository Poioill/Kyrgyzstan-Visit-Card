package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepo extends JpaRepository<Img, Long> {
    void deleteByTourId(Long tourId);
}
