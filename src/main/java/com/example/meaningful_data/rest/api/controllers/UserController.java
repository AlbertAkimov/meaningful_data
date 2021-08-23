package com.example.meaningful_data.rest.api.controllers;

import com.example.meaningful_data.rest.api.dto.UserDTO;
import com.example.meaningful_data.rest.api.models.User;
import com.example.meaningful_data.rest.api.security.jwt.JwtTokenProvider;
import com.example.meaningful_data.rest.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class UserController extends AbstractController<User, UserService> {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    protected UserController(UserService service, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        super(service, User.class);
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserDTO requestDTO) {

        try {

            String username = requestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));

            User user = service.findByUsername(username);

            if(user == null)
                throw new UsernameNotFoundException("User with username: " + username + "not found");

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity register(@RequestBody UserDTO requestDTO) {

        String username = requestDTO.getUsername();

        User verifiable = service.findByUsername(username);

        if(verifiable != null)
            throw new UsernameNotFoundException("username: " + username + " already exist");

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());

        User registeredUser = service.register(user);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));

        Map<Object, Object> response = new HashMap<>();
        response.put("username", registeredUser.getUsername());

        return ResponseEntity.ok(response);

    }
}
