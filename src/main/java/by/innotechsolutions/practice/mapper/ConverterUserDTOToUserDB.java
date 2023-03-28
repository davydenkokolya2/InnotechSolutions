package by.innotechsolutions.practice.mapper;

import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.entity.User;

public class ConverterUserDTOToUserDB {
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
