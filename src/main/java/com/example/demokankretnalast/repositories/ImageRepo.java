package com.example.demokankretnalast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demokankretnalast.entity.Img;


public interface ImageRepo extends JpaRepository<Img, Long> {
}
