package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.ImgRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ImgRegionRepo extends JpaRepository<ImgRegion, Long> {
}
