package com.example.springboot;

import java.util.ArrayList;
import java.util.Objects;

public class UserRepository {
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
