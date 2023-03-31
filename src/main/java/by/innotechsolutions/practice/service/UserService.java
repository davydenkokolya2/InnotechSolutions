package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.entity.User;
import by.innotechsolutions.practice.mapper.ConverterUserDTOToUserDB;
import by.innotechsolutions.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public List<User> findUserByEmail(User user) {
        return userRepository.findByEmail(user.getEmail());
    }

    public Boolean saveUser(UserDTO user) {
        ConverterUserDTOToUserDB converterUserDTOToUserDB = new ConverterUserDTOToUserDB();
        User userDB = converterUserDTOToUserDB.toEntity(user);
        //System.out.println(findUserByEmail(userDB));
        if (findUserByEmail(userDB).isEmpty()) {
                userRepository.save(userDB);
            return true;
        } else return false;
    }
}
