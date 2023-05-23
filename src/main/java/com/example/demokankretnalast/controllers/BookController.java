package com.example.demokankretnalast.controllers;

import com.example.demokankretnalast.entity.BookTour;
import com.example.demokankretnalast.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BookController {
    public final BookService bookService;

    @PostMapping("/book")
    public String booking(BookTour bookTour){
        bookService.saveBooking(bookTour);
        return "redirect:/tours";
    }
    @PostMapping("/book/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin";
    }
}
