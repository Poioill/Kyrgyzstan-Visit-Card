package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Sightseeing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightseeingRepo extends JpaRepository<Sightseeing, Long> {
    Sightseeing findByRegionsId(Long id);
}
