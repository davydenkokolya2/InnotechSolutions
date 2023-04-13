package by.innotechsolutions.practice;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUserDetails extends User {
    public final Long id;

    public final String firstName;
    public final String lastName;

    public JwtUserDetails(final Long id, final String username, final String hash,
                          final String firstName, final String lastName,
                          final Collection<? extends GrantedAuthority> authorities) {
        super(username, hash, authorities);
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
