package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.Regions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepo extends JpaRepository<Regions, Long> {
}
