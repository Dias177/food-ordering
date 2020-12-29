package kz.epam.tcfp.foodordering.entity;

import java.util.Objects;

public class Role implements Comparable<Role> {

    private int id;
    private String name;

    public Role() {}

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(Role role) {
        this.id = role.id;
        this.name = role.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role copy() {
        return new Role(this);
    }

    @Override
    public int compareTo(Role o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}