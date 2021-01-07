package kz.epam.tcfp.foodordering.entity;

import java.util.Objects;

public class OrderDetail extends Entity implements Comparable<OrderDetail> {

    private int orderId;
    private int foodId;
    private double price;
    private int quantity;

    public OrderDetail() {}

    public OrderDetail(int orderId, int foodId, double price, int quantity) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(OrderDetail o) {
        int result = (int) (this.price - o.price);
        if (result == 0) {
            result = this.quantity - o.quantity;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return orderId == that.orderId && foodId == that.foodId && Double.compare(that.price, price) == 0 &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, foodId, price, quantity);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", foodId=" + foodId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}