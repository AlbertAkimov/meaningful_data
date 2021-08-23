package com.example.meaningful_data.rest.api.security;

import com.example.meaningful_data.rest.api.models.User;
import com.example.meaningful_data.rest.api.security.jwt.JwtUserFactory;
import com.example.meaningful_data.rest.api.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User with username: " + username + " not found");

        return JwtUserFactory.create(user);
    }
}
