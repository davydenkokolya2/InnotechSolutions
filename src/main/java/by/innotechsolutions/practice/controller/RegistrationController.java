package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@AllArgsConstructor
@RestController
public class RegistrationController {
    private final UserService userService;


    @RequestMapping(value = "/registration", method = POST)
    @ResponseBody
    public String createUser(@RequestBody UserDTO userDTO) {

        return userService.saveUser(userDTO);
    }

}
