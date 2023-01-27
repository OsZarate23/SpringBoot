package com.bezkoder.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.data.mongodb.model.Books;
import com.bezkoder.spring.data.mongodb.repository.BooksRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class BooksController {
    @Autowired
    BooksRepository booksRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        try {
            List<Books> books = new ArrayList<Books>();
            booksRepository.findAll().forEach(books::add);
            System.out.println(books.toString());
            if (books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Books> getBook(@PathVariable("id") String id) {
        Optional<Books> book = booksRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/createbook")
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        try {
            Books books = booksRepository.save(
                    new Books(
                            book.getPrice(),
                            book.getAuthor(),
                            book.getEditorial(),
                            book.getName(),
                            book.getYearPublishied(),
                            book.getBrand(),
                            book.getModel()));
            return new ResponseEntity<>(books, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatebook/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable("id") String id, @RequestBody() Books book) {
        Optional<Books> book_data = booksRepository.findById(id);

        if (book_data.isPresent()) {
            Books book_search = book_data.get();
            book_search.setPrice(book.getPrice());
            book_search.setAuthor(book.getAuthor());
            book_search.setEditorial(book.getEditorial());
            book_search.setName(book.getName());
            book_search.setYear(book.getYearPublishied());
            book_search.setBrand(book.getBrand());
            book_search.setModel(book.getModel());
            return new ResponseEntity<>(booksRepository.save(book_search), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<Books> deleteBook(@PathVariable("id") String id) {
        try {
            booksRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
