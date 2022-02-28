package com.lukomski.wojtek.CarRentalApp.controller;

import com.lukomski.wojtek.CarRentalApp.db.UserRepository;
import com.lukomski.wojtek.CarRentalApp.exceptions.UserAlreadyExistException;
import com.lukomski.wojtek.CarRentalApp.model.User;
import com.lukomski.wojtek.CarRentalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @PostMapping(value = "/user/add", consumes = "application/json")
    ResponseEntity<User> add(@RequestBody User user) {
        Optional<User> user1 = Optional.of(userService.add(user));
        return ResponseEntity.of(user1);
    }

    @GetMapping("/user/all")
    List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/login/{login}")
    User getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<User> getUserBy(@PathVariable Integer userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @ExceptionHandler (UserAlreadyExistException.class)
        ResponseEntity<String> handlerExceptions(){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist, please choose different login");
        }
    }
