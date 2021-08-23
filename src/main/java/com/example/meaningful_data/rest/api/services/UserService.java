package com.example.meaningful_data.rest.api.services;

import com.example.meaningful_data.rest.api.models.Role;
import com.example.meaningful_data.rest.api.models.Status;
import com.example.meaningful_data.rest.api.models.User;
import com.example.meaningful_data.rest.api.repositories.RoleRepository;
import com.example.meaningful_data.rest.api.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */

@Service
public class UserService extends AbstractCRUDService<User, UserRepository> {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    protected UserService(UserRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        super(repository, User.class);
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {

        Role roleUser = roleRepository.findByName("ROLE_USER");

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        save(user);

        return user;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
