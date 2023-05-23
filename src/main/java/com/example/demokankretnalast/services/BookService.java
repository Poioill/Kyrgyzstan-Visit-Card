package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.BookTour;
import com.example.demokankretnalast.repositories.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public List<BookTour> allBooking(){
        return bookRepo.findAll();
    }
    public void deleteBook(Long id){ bookRepo.deleteById(id);
    }
    public void saveBooking(BookTour book){
        book.setTourName(book.getTourName());
        log.info("Saving new BookTour. TourName: {}, Name: {}; Email: {}, PhoneNumber: {}; Description: {}", book.getTourName(), book.getName(), book.getEmail(), book.getPhoneNumber(), book.getDescription());
        bookRepo.save(book);
    }
}
