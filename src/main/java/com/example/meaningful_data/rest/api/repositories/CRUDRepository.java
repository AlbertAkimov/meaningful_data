package com.example.meaningful_data.rest.api.repositories;

import com.example.meaningful_data.rest.api.models.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */

@NoRepositoryBean
public interface CRUDRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
}
