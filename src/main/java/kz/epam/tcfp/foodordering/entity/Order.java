package kz.epam.tcfp.foodordering.entity;

import java.util.Date;
import java.util.Objects;

public class Order implements Comparable<Order> {

    private int id;
    private int userId;
    private int orderStatusId;
    private double amount;
    private String city;
    private String address;
    private String phoneNumber;
    private Date date;

    public Order() {}

    public Order(int id, int userId, int orderStatusId, double amount, String city, String address, String phoneNumber,
                 Date date) {
        this.id = id;
        this.userId = userId;
        this.orderStatusId = orderStatusId;
        this.amount = amount;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public Order(Order order) {
        this.id = order.id;
        this.userId = order.userId;
        this.orderStatusId = order.orderStatusId;
        this.amount = order.amount;
        this.city = order.city;
        this.address = order.address;
        this.phoneNumber = order.phoneNumber;
        this.date = order.date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order copy() {
        return new Order(this);
    }

    @Override
    public int compareTo(Order o) {
        int result = (int) (this.amount - o.amount);
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
        return id == order.id && userId == order.userId && orderStatusId == order.orderStatusId && Double.compare(order.amount, amount) == 0 && Objects.equals(city, order.city) && Objects.equals(address, order.address) && Objects.equals(phoneNumber, order.phoneNumber) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderStatusId, amount, city, address, phoneNumber, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderStatusId=" + orderStatusId +
                ", amount=" + amount +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", date=" + date +
                '}';
    }
}