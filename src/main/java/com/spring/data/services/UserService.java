package com.spring.data.services;

import com.spring.data.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.spring.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository usersRepository;

    public boolean emailExists(String email){
        List<Users> users = usersRepository.findByEmail(email);
        return !users.isEmpty();
    }

    public ResponseEntity<Object> create (Users users) {
            return emailExists(users.getEmail())
                ? new ResponseEntity<>("Email existence: " + users.getEmail(), HttpStatus.CONFLICT)
                    : new ResponseEntity<>(usersRepository.save(users), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getById ( Long id ) {
            Optional<Users> searchId = usersRepository.findById(id);
            return usersRepository.findById(id).isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(searchId, HttpStatus.OK);
    }

    public ResponseEntity<List<Users>> getAll() {
            List<Users> users = usersRepository.findAll();
            return users.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                        : new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Users user) {
        if (emailExists(user.getEmail())) {
                return new ResponseEntity<>("Email existence: " + user.getEmail(), HttpStatus.CONFLICT);
        } else {
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
}