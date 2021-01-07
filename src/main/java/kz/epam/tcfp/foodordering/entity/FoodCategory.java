package kz.epam.tcfp.foodordering.entity;

import java.util.Objects;

public class FoodCategory extends Entity implements Comparable<FoodCategory> {

    private String name;

    public FoodCategory() {}

    public FoodCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                '}';
    }
}