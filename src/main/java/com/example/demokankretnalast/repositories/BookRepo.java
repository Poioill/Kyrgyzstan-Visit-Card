package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.BookTour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookTour, Long> {
}
