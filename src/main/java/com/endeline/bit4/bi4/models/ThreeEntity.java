package com.endeline.bit4.bi4.models;

import lombok.Data;

import javax.persistence.*;

/**
 * This class is for hibernate create table and for rest communication.
 */
@Table(name = "three")
@Entity
@Data
public class ThreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parentId")
    private int parentId;

    @Column(name = "value")
    private int value;

    //use in dao impl when we don't have any result
    public ThreeEntity() {
    }

    //use in test
    public ThreeEntity(int parentId, int value) {
        this.parentId = parentId;
        this.value = value;
    }

    //use in test
    public ThreeEntity(int id, int parentId, int value) {
        this.id = id;
        this.parentId = parentId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getParentId() {
        return parentId;
    }

    public long getValue() {
        return value;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
