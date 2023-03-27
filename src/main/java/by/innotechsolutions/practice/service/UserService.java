package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.entity.UserDB;
import by.innotechsolutions.practice.entity.UserDTO;
import by.innotechsolutions.practice.entity.ConverterUserDTOToUserDB;
import by.innotechsolutions.practice.repository.LocalUserRepository;
import by.innotechsolutions.practice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, LocalUserRepository localUserRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDB> findUserByEmail(UserDB user) {
        return userRepository.findByEmail(user.getEmail());
    }

    public Boolean saveUser(UserDTO user) {
        ConverterUserDTOToUserDB converterUserDTOToUserDB = new ConverterUserDTOToUserDB();
        UserDB userDB = converterUserDTOToUserDB.convert(user);
        System.out.println(findUserByEmail(userDB));
        if (findUserByEmail(userDB).isEmpty()) {
                userRepository.save(userDB);
            return true;
        } else return false;
    }
}
