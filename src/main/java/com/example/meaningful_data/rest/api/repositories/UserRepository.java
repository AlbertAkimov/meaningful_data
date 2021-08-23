package com.example.meaningful_data.rest.api.repositories;

import com.example.meaningful_data.rest.api.models.User;
import org.springframework.stereotype.Repository;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@Repository
public interface UserRepository extends CRUDRepository<User> {

    User findByUsername(String username);
}
