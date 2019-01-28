package com.endeline.bit4.bi4.dao.three;

import com.endeline.bit4.bi4.enums.Status;
import com.endeline.bit4.bi4.models.ThreeEntity;

import java.util.List;

/**
 * Public function of this dao.
 */
public interface ThreeDao {

    /**
     * This function find all {@link ThreeEntity} in database.
     * @return a {@link List} with all {@link ThreeEntity}.
     */
    List<ThreeEntity> findAll();

    /**
     * This function is to find only one {@link ThreeEntity}.
     * We just get all data and filter they.
     * @param id id of search entity.
     * @return found {@link ThreeEntity}.
     */
    ThreeEntity findById(int id);

    /**
     * This function find all entities where parentId equals id.
     * @param id parentId.
     * @return {@link List} of {@link ThreeEntity}
     */
    List<ThreeEntity> findByParentId(int id);

    /**
     * Function save new entity.
     * @param entity {@link ThreeEntity} to save.
     * @return saved {@link ThreeEntity}.
     */
    ThreeEntity save(ThreeEntity entity);

    /**
     * Function to update entity with new params.
     * @param entity to update.
     * @return updated {@link ThreeEntity}.
     */
    ThreeEntity update(ThreeEntity entity);

    /**
     * Function for delete entity from database by entity.
     * @param entity to delete
     * @return {@link Status} of deleted. Function of delete is void, so always we return OK.
     */
    Status delete(ThreeEntity entity);

    /**
     * Function for delete entity from database by entity id.
     * @param id entity to delete.
     * @return {@link Status} of deleted. Function of delete is void, so always we return OK.
     */
    Status deleteById(int id);
}
