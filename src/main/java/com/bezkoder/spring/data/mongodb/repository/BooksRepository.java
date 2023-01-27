package com.bezkoder.spring.data.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bezkoder.spring.data.mongodb.model.Books;

public interface BooksRepository extends MongoRepository<Books, String> {

}
