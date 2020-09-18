package com.quizzy.quizzy;

import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all(){
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable String id){
        return repository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable int id){

        return repository.findById(String.valueOf(id))
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return repository.save(user);
                })
                .orElseGet(()->{
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable String id){
        repository.deleteById(id);
    }
}
