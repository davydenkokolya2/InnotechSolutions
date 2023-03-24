package com.example.practice.service;

import com.example.practice.entity.UserDTO;
import com.example.practice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findUserByEmail(UserDTO user) {
        return userRepository.findUserByEmail(user);
    }

    public Boolean saveUser(UserDTO user) {
        if (findUserByEmail(user) == null) {
            userRepository.save(user);
            return true;
        } else return false;
    }
}
