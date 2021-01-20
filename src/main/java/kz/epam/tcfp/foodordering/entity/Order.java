package kz.epam.tcfp.foodordering.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Order extends Entity implements Comparable<Order> {

    private long userId;
    private long orderStatusId;
    private double price;
    private Timestamp date;

    public Order() {}

    public Order(long userId, long orderStatusId, double price, Timestamp date) {
        this.userId = userId;
        this.orderStatusId = orderStatusId;
        this.price = price;
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public int compareTo(Order o) {
        int result = (int) (this.price - o.price);
        if (result == 0) {
            result = this.date.compareTo(o.date);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return userId == order.userId && orderStatusId == order.orderStatusId &&
                Double.compare(order.price, price) == 0 && date.equals(order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderStatusId, price, date);
    }
}