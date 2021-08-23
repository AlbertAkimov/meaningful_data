package com.example.meaningful_data.rest.api.services;

import com.example.meaningful_data.rest.api.models.AbstractEntity;
import com.example.meaningful_data.rest.api.repositories.CRUDRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@Slf4j
public abstract class AbstractCRUDService
        <E extends AbstractEntity, R extends CRUDRepository<E>> implements CRUDService<E> {

    protected final R repository;
    private final String clazz;

    protected AbstractCRUDService(R repository, Class<? extends AbstractEntity> clazz) {
        this.repository = repository;
        this.clazz = clazz.getName();
    }

    @Override
    public E getById(Long id) {

        E result = repository.findById(id).orElse(null);

        if(result != null)
            log.info("entity: " + clazz + " successfully found by id: " + id);

        return result;

    }

    @Override
    public E save(E entity) {

        E result = repository.save(entity);
        log.info("entity: " + clazz + " successfully saved");
        return result;

    }

    @Override
    public List<E> getAll() {

        List<E> result = repository.findAll();

        if(!result.isEmpty())
            log.info("list entities: " + clazz + " successfully load");

        return result;
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
        log.info("entity: " + clazz + " successfully deleted by id: " + id);

    }
}
