package by.innotechsolutions.practice.repository;

import by.innotechsolutions.practice.entity.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;

@Repository
public class LocalUserRepository {
    ArrayList<UserDTO> list = new ArrayList<>();

    public void save(UserDTO user) {
        list.add(user);
    }

    public UserDTO findUserByEmail(UserDTO user) {
        for (UserDTO item : list)
            if (Objects.equals(item.getEmail(), user.getEmail()))
                return item;
        return null;

    }
}
