package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.entity.User;
import by.innotechsolutions.practice.repository.UserRepository;
import by.innotechsolutions.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@AllArgsConstructor
@RestController
public class LoginController {

    UserService userService;
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = POST)
    @ResponseBody
    public User createUser(@RequestBody User user) {
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
