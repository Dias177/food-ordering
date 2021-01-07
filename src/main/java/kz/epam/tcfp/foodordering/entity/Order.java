package kz.epam.tcfp.foodordering.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Order extends Entity implements Comparable<Order> {

    private long userId;
    private long orderStatusId;
    private double amount;
    private String city;
    private String address;
    private Timestamp timestamp;
    private String comment;

    public Order() {}

    public Order(long userId, long orderStatusId, double amount, String city, String address,
                 Timestamp timestamp, String comment) {
        this.userId = userId;
        this.orderStatusId = orderStatusId;
        this.amount = amount;
        this.city = city;
        this.address = address;
        this.timestamp = timestamp;
        this.comment = comment;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(Order o) {
        int result = (int) (this.amount - o.amount);
        if (result == 0) {
            result = this.timestamp.compareTo(o.timestamp);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return userId == order.userId && orderStatusId == order.orderStatusId &&
                Double.compare(order.amount, amount) == 0 && city.equals(order.city) && address.equals(order.address)
                && timestamp.equals(order.timestamp) && Objects.equals(comment, order.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderStatusId, amount, city, address, timestamp, comment);
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", orderStatusId=" + orderStatusId +
                ", amount=" + amount +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", timestamp=" + timestamp +
                ", comment='" + comment + '\'' +
                '}';
    }
}