package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepo extends JpaRepository<Regions, Long> {
    List<Regions> findByRegionFullName(String regionFullName);
}
