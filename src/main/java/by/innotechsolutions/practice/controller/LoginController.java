package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.entity.User;
import by.innotechsolutions.practice.repository.UserRepository;
import by.innotechsolutions.practice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LoginController {

    UserService userService;
    UserRepository userRepository;

    public LoginController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = POST)
    @ResponseBody
    public List<User> createUser(@RequestBody User user) {
        return userService.findUserByEmail(user);
    }

    @RequestMapping(value = "/login", method = GET)
    @ResponseBody
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/loginn", method = GET)
    @ResponseBody
    public boolean get() {
        return true;
    }
}
