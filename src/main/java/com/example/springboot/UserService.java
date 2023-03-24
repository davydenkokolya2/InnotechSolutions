package com.example.springboot;

public class UserService {

    UserRepository userRepository = new UserRepository();

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
