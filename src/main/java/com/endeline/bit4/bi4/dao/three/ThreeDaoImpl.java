package com.endeline.bit4.bi4.dao.three;

import com.endeline.bit4.bi4.enums.Status;
import com.endeline.bit4.bi4.models.ThreeEntity;
import com.endeline.bit4.bi4.repository.ThreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is layer of data access.
 * This class is implementation of ThreeDao public functionality.
 * implements {@link ThreeDao}
 */
@Repository
@Qualifier(value = "threeDao")
public class ThreeDaoImpl implements ThreeDao {

    @Autowired
    private ThreeRepository repository;

    @Override
    public List<ThreeEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ThreeEntity findById(int id) {
        return repository.findAll()
                .stream()
                .filter(entity -> entity.getId() == id)
                .findAny()
                .orElseGet(null);
    }

    @Override
    public List<ThreeEntity> findByParentId(int id) {
        return repository.findAll()
                .stream()
                .filter(entity -> entity.getParentId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public ThreeEntity save(ThreeEntity entity) {
        return repository.save(entity);

    }

    @Override
    public ThreeEntity update(ThreeEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Status delete(ThreeEntity entity) {
        repository.delete(entity);
        return Status.OK;
    }

    @Override
    public Status deleteById(int id) {
        //find entity with this id.
        ThreeEntity entity = repository.findAll()
                .stream()
                .filter(filter -> filter.getId() == id)
                .findAny()
                .orElseGet(ThreeEntity::new);

        repository.delete(entity);

        return Status.OK;
    }
}
