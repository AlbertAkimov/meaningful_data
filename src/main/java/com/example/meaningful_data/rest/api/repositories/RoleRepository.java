package com.example.meaningful_data.rest.api.repositories;

import com.example.meaningful_data.rest.api.models.Role;
import org.springframework.stereotype.Repository;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@Repository
public interface RoleRepository extends CRUDRepository<Role> {

    Role findByName(String name);
}
