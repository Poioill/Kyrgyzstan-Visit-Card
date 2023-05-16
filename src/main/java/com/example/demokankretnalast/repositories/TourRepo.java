package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.Img;
import com.example.demokankretnalast.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepo extends JpaRepository<Tour, Long> {
    List<Tour> findByTitleContainingIgnoreCase(String title);
}
