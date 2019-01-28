package com.endeline.bit4.bi4.service.three;

import com.endeline.bit4.bi4.dao.three.ThreeDao;
import com.endeline.bit4.bi4.models.ManyThreeEntity;
import com.endeline.bit4.bi4.models.StatusEntity;
import com.endeline.bit4.bi4.models.ThreeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This is middle layout between controller and dao.
 * Class is an implementation of {@link ThreeService}.
 * implements {@link ThreeService}.
 */
@Service
public class ThreeServiceImpl implements ThreeService {

    @Autowired
    private ThreeDao threeDao;

    @Override
    public List<ThreeEntity> findAll() {
        return threeDao.findAll();
    }

    @Override
    public ThreeEntity findById(int id) {
        return threeDao.findById(id);
    }

    @Override
    public List<ThreeEntity> findByParentId(int id) {
        return threeDao.findByParentId(id);
    }

    @Override
    public ThreeEntity save(ThreeEntity entity) {
        return threeDao.save(entity);
    }

    @Override
    public ThreeEntity update(ThreeEntity entity) {
        return threeDao.update(entity);
    }

    @Override
    public StatusEntity delete(ThreeEntity entity) {
        return new StatusEntity(threeDao.delete(entity).getName());
    }

    @Override
    public StatusEntity deleteById(int id) {
        return new StatusEntity(threeDao.deleteById(id).getName());
    }

    @Override
    public List<ThreeEntity> updateMany(ManyThreeEntity manyThreeEntity) {
        List<ThreeEntity> entities = new ArrayList<>();

        manyThreeEntity.getEntities().forEach(threeEntity -> entities.add(threeDao.update(threeEntity)));

        return entities;
    }
}
