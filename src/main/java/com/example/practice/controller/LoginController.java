package com.example.practice.controller;

import com.example.practice.entity.UserDB;
import com.example.practice.repository.UserRepository;
import com.example.practice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    UserService userService;
    UserRepository userRepository;

    public LoginController(UserRepository userRepository, UserService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/login")
    public List<UserDB> createUser(@RequestBody UserDB user) {
        return userService.findUserByEmail(user);
    }

    @GetMapping("/login")
    public List<UserDB> getUsers() {
        return userRepository.findAll();
    }
}
