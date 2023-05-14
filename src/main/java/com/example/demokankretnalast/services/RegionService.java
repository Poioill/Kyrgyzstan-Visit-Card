package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Tour;
import com.example.demokankretnalast.repositories.RegionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegionService {
    private final RegionRepo regionRepo;

    public RegionService(RegionRepo regionRepo){
        this.regionRepo = regionRepo;
    }

    public Iterable<Regions> findAllRegions(){
        return regionRepo.findAll();
    }

    public Regions getRegionById(Long id) {
        return regionRepo.findById(id).orElseThrow(null);
    }
}
