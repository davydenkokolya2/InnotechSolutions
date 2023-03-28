package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/registration")
    public Boolean createUser(@RequestBody UserDTO userDTO) {

        return userService.saveUser(userDTO);
    }

}
