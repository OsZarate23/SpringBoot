package com.spring.data.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.data.model.mongo.Users;

import java.util.List;
public interface UsersRepository extends MongoRepository<Users, String> {
    List<Users> findByEmail(String email);
}
