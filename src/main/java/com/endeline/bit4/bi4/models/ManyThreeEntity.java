package com.endeline.bit4.bi4.models;

import lombok.Data;

import java.util.List;

/**
 * Class for update or save many entities.
 */
@Data
public class ManyThreeEntity {
    private List<ThreeEntity> entities;

    public ManyThreeEntity() {
    }

    //user in test
    public ManyThreeEntity(List<ThreeEntity> entities) {
        this.entities = entities;
    }

    public List<ThreeEntity> getEntities() {
        return entities;
    }
}
