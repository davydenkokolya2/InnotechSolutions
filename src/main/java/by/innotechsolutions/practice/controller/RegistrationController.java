package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = POST)
    @ResponseBody
    public Boolean createUser(@RequestBody UserDTO userDTO) {

        return userService.saveUser(userDTO);
    }

}
