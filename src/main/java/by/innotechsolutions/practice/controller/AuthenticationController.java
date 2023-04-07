package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.AuthenticationResponse;
import by.innotechsolutions.practice.DTO.UserDTO;
import by.innotechsolutions.practice.service.JwtTokenService;
import by.innotechsolutions.practice.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@AllArgsConstructor
@RestController
public class AuthenticationController {
    JwtTokenService jwtTokenService;
    JwtUserDetailsService jwtUserDetailsService;
    AuthenticationManager authenticationManager;

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
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getEmail());
        System.out.println(userDetails);
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        System.out.println(authenticationResponse);
        return authenticationResponse;
    }
}
