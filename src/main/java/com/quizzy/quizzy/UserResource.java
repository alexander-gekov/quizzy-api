package com.quizzy.quizzy;

import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserResource {
    private final UserRepository repository;

    UserResource(UserRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/users")
    public List<User> all() {
        return repository.findAll();
    }

    @CrossOrigin
    @PostMapping("/users")
    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id-" + id);

        return user.get();
    }

    @CrossOrigin
    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable int id) {

        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }

        newUser.setId(id);
        repository.save(newUser);
        return newUser;
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public String deleteEmployee(@PathVariable int id) {
        repository.deleteById(id);
        return "Successfully deleted user with id : " + id;
    }
}
