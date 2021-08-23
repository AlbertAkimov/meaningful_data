package com.example.meaningful_data.rest.api.services;

import com.example.meaningful_data.rest.api.models.AbstractEntity;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */

public interface CRUDService<E extends AbstractEntity> {

    E getById(Long id);
    E save(E entity);
    List<E> getAll();
    void delete(Long id);

}
