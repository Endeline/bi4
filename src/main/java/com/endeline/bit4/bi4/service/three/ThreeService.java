package com.endeline.bit4.bi4.service.three;

import com.endeline.bit4.bi4.models.ManyThreeEntity;
import com.endeline.bit4.bi4.models.StatusEntity;
import com.endeline.bit4.bi4.models.ThreeEntity;

import java.util.List;

/**
 * Public functionality of this service.
 */
public interface ThreeService {

    /**
     * Function get all entities from dao layer.
     * @return {@link List} of {@link ThreeEntity}
     */
    List<ThreeEntity> findAll();

    /**
     * Function get entity with current id from dao.
     * @param id to find.
     * @return found {@link ThreeEntity}
     */
    ThreeEntity findById(int id);

    /**
     * Call dao for find all entities where parentId equals id.
     * @param id parentId to find.
     * @return {@link List} with found {@link ThreeEntity}.
     */
    List<ThreeEntity> findByParentId(int id);

    /**
     * Call dao layer to save new enity.
     * @param entity {@link ThreeEntity} to save.
     * @return saved {@link ThreeEntity}.
     */
    ThreeEntity save(ThreeEntity entity);

    /**
     * Function call dao layer to update current entity.
     * @param entity {@link ThreeEntity} to update.
     * @return updated {@link ThreeEntity}
     */
    ThreeEntity update(ThreeEntity entity);

    /**
     * Function call dao to delete entity by entity.
     * @param entity {@link ThreeEntity} to delete.
     * @return delete {@link StatusEntity}.
     */
    StatusEntity delete(ThreeEntity entity);

    /**
     * Function call dao to delete entity by id.
     * @param id to delete.
     * @return delete {@link StatusEntity}.
     */
    StatusEntity deleteById(int id);

    List<ThreeEntity> updateMany(ManyThreeEntity manyThreeEntity);
}
