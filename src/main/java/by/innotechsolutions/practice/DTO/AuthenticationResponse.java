package by.innotechsolutions.practice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String accessToken;
    private String firstName;
    private String lastName;
    private Long userId;
}
