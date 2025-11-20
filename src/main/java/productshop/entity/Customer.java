package productshop.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Integer id;
    private String name;
    private String phoneNumber;
    private int orderCount;
    private boolean hasLoyaltyCard;
    private List<Order> orders = new ArrayList<>();

    public Customer(String name, String phoneNumber, boolean hasLoyaltyCard) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.orderCount = 0;
        this.hasLoyaltyCard = hasLoyaltyCard;
    }

    public Customer(Integer id, String name, String phoneNumber, boolean hasLoyaltyCard, int orderCount) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.hasLoyaltyCard = hasLoyaltyCard;
        this.orderCount = orderCount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public boolean isHasLoyaltyCard() {
        return hasLoyaltyCard;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderCount=" + orderCount +
                ", hasLoyaltyCard=" + hasLoyaltyCard +
                ", orders=" + orders +
                '}';
    }
}
