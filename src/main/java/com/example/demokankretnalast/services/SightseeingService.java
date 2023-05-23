package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.Regions;
import com.example.demokankretnalast.entity.Sightseeing;
import com.example.demokankretnalast.repositories.RegionRepo;
import com.example.demokankretnalast.repositories.SightseeingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.Region;

@Service
@Slf4j
@RequiredArgsConstructor
public class SightseeingService {
    private final RegionRepo regionRepo;
    private final SightseeingRepo sightseeingRepo;

    public void addSightseeing(Sightseeing sightseeing, Long regionId) {
        Regions region = new Regions();
        region.setId(regionId);
        sightseeing.setRegions(region);
        sightseeingRepo.save(sightseeing);
    }
}

