package kz.epam.tcfp.foodordering.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
