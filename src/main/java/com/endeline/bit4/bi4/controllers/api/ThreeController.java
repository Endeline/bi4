package com.endeline.bit4.bi4.controllers.api;

import com.endeline.bit4.bi4.models.ManyThreeEntity;
import com.endeline.bit4.bi4.models.StatusEntity;
import com.endeline.bit4.bi4.models.ThreeEntity;
import com.endeline.bit4.bi4.service.three.ThreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Class is for hold request from world to server.
 * Has function to find, add, update, delete three entities.
 */
@Controller
@RequestMapping(value = "/api")
public class ThreeController {

    @Autowired
    private ThreeService threeService;

    /**
     * This function hold GET request, when user wants to get all node.
     * @return {@link ResponseEntity} with all {@link ThreeEntity}
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<ThreeEntity>> finall() {
        return new ResponseEntity<>(
                threeService.findAll(),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * This function hold GET request, when user wants get one node entity by entity id.
     * @param id of {@link ThreeEntity}
     * @return {@link ResponseEntity} with ont {@link ThreeEntity}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ThreeEntity> findById(@PathVariable int id) {
        return new ResponseEntity<>(
                threeService.findById(id),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * Function hole GET request, when user wants get parent node by entity id.
     * @param id parent id.
     * @return {@link ResponseEntity} with {@link List} of {@link ThreeEntity}
     */
    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ThreeEntity>> findByParentId(@PathVariable int id) {
        return new ResponseEntity<>(
                threeService.findByParentId(id),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * Function hold POST request to add new entity.
     * @param entity to save.
     * @return {@link ResponseEntity} with {@link ThreeEntity}
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ThreeEntity> save(@RequestBody ThreeEntity entity) {
        return new ResponseEntity<>(
                threeService.save(entity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * This function hold PUT request, update existing entity with new params.
     * @param entity entity to update.
     * @return {@link ResponseEntity} with updated {@link ThreeEntity}
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ThreeEntity> update(@RequestBody ThreeEntity entity) {
        return new ResponseEntity<>(
                threeService.update(entity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * This function hold PUT request when user send request to update all entities.
     * @param manyThreeEntity Object with entities to update.
     * @return {@link List} with updated {@link ThreeEntity}.
     */
    @RequestMapping(value = "/manyupdate", method = RequestMethod.PUT)
    public ResponseEntity<List<ThreeEntity>> manyUpdate(@RequestBody ManyThreeEntity manyThreeEntity) {
        return new ResponseEntity<>(
                threeService.updateMany(manyThreeEntity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * This function hold DELETE request, when user wants delete entity by entity.
     * @param entity entity to delete.
     * @return {@link ResponseEntity} with {@link StatusEntity}
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<StatusEntity> delete(@RequestBody ThreeEntity entity) {
        return new ResponseEntity<>(
                threeService.delete(entity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * This function hold DELETE request, when user wants delete entity by entity id.
     * @param id entity to delete.
     * @return {@link ResponseEntity} with {@link StatusEntity}
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusEntity> deleteById(@PathVariable int id) {
        return new ResponseEntity<>(
                threeService.deleteById(id),
                new HttpHeaders(),
                HttpStatus.OK);
    }
}
