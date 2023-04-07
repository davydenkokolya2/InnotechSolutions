package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.JwtUserDetails;
import by.innotechsolutions.practice.entity.User;
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
        System.out.println(username);
        final User client = userRepository.findByEmail(username);/*.orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found"));*/
        System.out.println(client.getPassword());
        return new JwtUserDetails(
                client.getId(),
                username,
                String.valueOf(client.hashCode()),
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    }

}
