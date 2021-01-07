package kz.epam.tcfp.foodordering.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
