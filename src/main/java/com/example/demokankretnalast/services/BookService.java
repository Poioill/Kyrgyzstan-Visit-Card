package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.BookTour;
import com.example.demokankretnalast.repositories.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    public void saveBooking(BookTour book){
        log.info("Saving new BookTour. Name: {}; Email: {}, PhoneNumber: {}; Description: {}", book.getName(), book.getEmail(), book.getPhoneNumber(), book.getDescription());
        bookRepo.save(book);
    }
}
