package com.example.meaningful_data.rest.api.controllers;

import com.example.meaningful_data.rest.api.models.AbstractEntity;
import com.example.meaningful_data.rest.api.services.CRUDService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */

public class AbstractController<E extends AbstractEntity, S extends CRUDService<E>> {

    protected final S service;
    public Class<E> clazz;

    public AbstractController(S service, Class<E> clazz) {
        this.service = service;
        this.clazz = clazz;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<E> getById(@PathVariable("id") Long id) {

        if(id == null)
            return new ResponseEntity<E>(HttpStatus.BAD_REQUEST);

        E entity = service.getById(id);

        if(entity == null)
            return new ResponseEntity<E>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<E>(entity, HttpStatus.OK);
    }

    /**
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<E> save(@RequestBody @Validated E entity) {

        HttpHeaders headers = new HttpHeaders();

        if (entity == null) {
            return new ResponseEntity<E>(HttpStatus.BAD_REQUEST);
        }

        this.service.save(entity);

        return new ResponseEntity<E>(entity, headers, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<E> delete(@PathVariable("id") Long id) {

        E entity = service.getById(id);

        if(entity == null)
            return new ResponseEntity<E>(HttpStatus.NOT_FOUND);

        this.service.delete(id);

        return new ResponseEntity<E>(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<E>> getAll() {

        List<E> entities = this.service.getAll();

        if(entities.isEmpty())
            return new ResponseEntity<List<E>>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<E>>(entities, HttpStatus.OK);
    }
}
