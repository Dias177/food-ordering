package kz.epam.tcfp.foodordering.entity;

import java.util.Objects;

public class OrderStatus implements Comparable<OrderStatus> {

    private int id;
    private String name;

    public OrderStatus() {}

    public OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public OrderStatus(OrderStatus orderStatus) {
        this.id = orderStatus.id;
        this.name = orderStatus.name;
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

    public OrderStatus copy() {
        return new OrderStatus(this);
    }


    @Override
    public int compareTo(OrderStatus o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}