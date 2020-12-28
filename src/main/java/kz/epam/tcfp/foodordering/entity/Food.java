package kz.epam.tcfp.foodordering.entity;

import java.util.Objects;

public class Food implements Comparable<Food> {

    private int id;
    private int foodCategoryId;
    private String name;
    private String description;
    private double price;

    public Food() {}

    public Food(Food food) {
        this.id = food.id;
        this.foodCategoryId = food.foodCategoryId;
        this.name = food.name;
        this.description = food.description;
        this.price = food.price;
    }

    public Food(int foodCategoryId, String name, double price) {
        this.foodCategoryId = foodCategoryId;
        this.name = name;
        this.price = price;
    }

    public Food(int foodCategoryId, String name, String description, double price) {
        this.foodCategoryId = foodCategoryId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Food copy() {
        return new Food(this);
    }

    @Override
    public int compareTo(Food f) {
        int result = (int) (this.price - f.price);
        if (result == 0) {
            result = this.name.compareTo(f.name);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return foodCategoryId == food.foodCategoryId && Double.compare(food.price, price) == 0 && name.equals(food.name) &&
                Objects.equals(description, food.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodCategoryId, name, description, price);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodCategory=" + foodCategoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}