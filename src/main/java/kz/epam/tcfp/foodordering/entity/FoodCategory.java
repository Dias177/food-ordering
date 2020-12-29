package kz.epam.tcfp.foodordering.entity;

import java.util.Objects;

public class FoodCategory implements Comparable<FoodCategory> {

    private int id;
    private String name;

    public FoodCategory() {}

    public FoodCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FoodCategory(FoodCategory foodCategory) {
        this.id = foodCategory.id;
        this.name = foodCategory.name;
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

    public FoodCategory copy() {
        return new FoodCategory(this);
    }

    @Override
    public int compareTo(FoodCategory o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodCategory that = (FoodCategory) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "FoodCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}