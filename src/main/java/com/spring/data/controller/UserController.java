package com.spring.data.controller;

import com.spring.data.model.Users;
import com.spring.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;

    /**
     * @return list users in db
     */
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return userService.getAll();
    }

    /**
     * @return search user by id in bd
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    /**
     * @return create user in bd
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createUsers(@RequestBody Users users) {
        return userService.create(users);
    }

    /**
     * @return update user in bd
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        return userService.update(id, userDetails);
    }
}