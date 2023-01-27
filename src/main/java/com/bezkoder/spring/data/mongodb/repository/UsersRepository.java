package com.bezkoder.spring.data.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bezkoder.spring.data.mongodb.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

}
