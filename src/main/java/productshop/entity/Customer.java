package productshop.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Integer id;
    private String name;
    private String phoneNumber;
    private int orderCount;
    private boolean hasLoyaltyCard;
    private List<Order> ordersHistory = new ArrayList<>();
    private Order currentdOrder;

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

    public Order getCurrentdOrder() {
        return currentdOrder;
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

    public List<Order> getOrdersHistory() {
        return ordersHistory;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderCount=" + orderCount +
                ", hasLoyaltyCard=" + hasLoyaltyCard +
                ", orders=" + ordersHistory +
                '}';
    }

    public void createOrder() {
        currentdOrder = new Order(this.id, 0);
    }

    public void addProductToOrder(Product product) {
        if(currentdOrder == null) {
            throw new RuntimeException("Ошибка, заказа не существует");
        }
        currentdOrder.addProductToBasket(product);
    }



}
