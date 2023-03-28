package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.entity.User;
import by.innotechsolutions.practice.repository.UserRepository;
import by.innotechsolutions.practice.service.UserService;
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
    public List<User> createUser(@RequestBody User user) {
        return userService.findUserByEmail(user);
    }

    @GetMapping("/login")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
