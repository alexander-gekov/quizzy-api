package com.quizzy.quizzy;

import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class UserResource {

    @Autowired
    private final UserRepository repository;

    UserResource(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @CrossOrigin
    @PostMapping("/users")
    public User newUser(@RequestBody User newUser){
        User savedUser = repository.save(newUser);
        return savedUser;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        return user.get();
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable int id) {

        Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            return null;
        }

        newUser.setId(id);
        repository.save(newUser);
        return newUser;
    }

    @DeleteMapping("/users/{id}")
    public String deleteEmployee(@PathVariable int id) {
        repository.deleteById(id);
        return "Successfully deleted user with id : " + id;
    }
}
