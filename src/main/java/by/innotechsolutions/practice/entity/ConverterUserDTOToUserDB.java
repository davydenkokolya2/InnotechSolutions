package by.innotechsolutions.practice.entity;

public class ConverterUserDTOToUserDB {
    public UserDB convert(UserDTO userDTO) {
        UserDB userDB = new UserDB();
        userDB.setEmail(userDTO.getEmail());
        userDB.setPassword(userDTO.getPassword());
        userDB.setFirstName(userDTO.getFirstName());
        userDB.setLastName(userDTO.getLastName());
        return userDB;
    }

}
