package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.JwtUserDetails;
import by.innotechsolutions.practice.entity.User;
import by.innotechsolutions.practice.mapper.ConverterUserDTOToUserDB;
import by.innotechsolutions.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
       //System.out.println(username);
        final User client = userRepository.findByEmail(username);

        return new JwtUserDetails(
                client.getId(),
                username,
                String.valueOf(client.hashCode()),
                client.getFirstName(),
                client.getLastName(),
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    }

    public JwtUserDetails loadUserByUsername(final UserDTO user) {
        User client = userRepository.findByEmail(user.getEmail());
        //System.out.println(client.getPassword() + " " + client.getEmail() + " " + client.getFirstName());
        ConverterUserDTOToUserDB converterUserDTOToUserDB = new ConverterUserDTOToUserDB();
        if (client == null && !user.getFirstName().equals("") && !user.getLastName().equals("")) {
            User userDB = converterUserDTOToUserDB.toEntity(user);
            userRepository.save(userDB);
            client = userRepository.findByEmail(user.getEmail());
        }
        if (client.getPassword().equals(user.getPassword())) {
            return new JwtUserDetails(
                    client.getId(),
                    user.getEmail(),
                    String.valueOf(client.hashCode()),
                    client.getFirstName(),
                    client.getLastName(),
                    Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
        }
        else return null;
    }
}
