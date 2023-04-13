package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.AuthenticationResponse;
import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.JwtUserDetails;
import by.innotechsolutions.practice.service.JwtTokenService;
import by.innotechsolutions.practice.service.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final JwtTokenService jwtTokenService;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @RequestMapping(value = "/authenticate", method = POST)
    @ResponseBody
    public AuthenticationResponse authenticate(@RequestBody final UserDTO request) {
        /*try {
            System.out.println(request.getEmail() + request.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
            System.out.println("321");
        } catch (final BadCredentialsException ex) {
            System.out.println("123");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }*/
        System.out.println("AuthenticationController " + request);
        final JwtUserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request);
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        authenticationResponse.setUserId(userDetails.id);
        authenticationResponse.setFirstName(userDetails.firstName);
        authenticationResponse.setLastName(userDetails.lastName);
        return authenticationResponse;
    }
}
