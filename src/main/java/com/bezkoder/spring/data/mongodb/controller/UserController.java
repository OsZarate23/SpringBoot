package com.bezkoder.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.data.mongodb.model.Users;
import com.bezkoder.spring.data.mongodb.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UsersRepository usersRepository;

    /**
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> users = new ArrayList<Users>();
            usersRepository.findAll().forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUser(@PathVariable("id") String id) {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createuser")
    public ResponseEntity<Users> createUsers(@RequestBody Users users) {
        try {
            Users _users = usersRepository.save(
                    new Users(
                            users.getFisrtName(),
                            users.getLastName(),
                            users.getEmail(),
                            users.getPassword()));
            return new ResponseEntity<>(_users, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Users> updateTutorial(@PathVariable("id") String id, @RequestBody Users user) {
        Optional<Users> user_data = usersRepository.findById(id);

        if (user_data.isPresent()) {
            Users search_user = user_data.get();
            search_user.setFirstName(user.getFisrtName());
            search_user.setLastName(user.getLastName());
            search_user.setEmail(user.getEmail());
            search_user.setPassword(user.getPassword());
            return new ResponseEntity<>(usersRepository.save(search_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
