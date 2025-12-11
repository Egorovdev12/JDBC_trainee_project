package productshop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Integer id;
    private final Integer customerId;
    private final LocalDateTime orderDate;
    private List<Product> basket = new ArrayList<>();
    private double price;

    public Order(Integer id) {
        this.id = id;
        this.customerId = this.getCustomerId();
        this.orderDate = LocalDateTime.now();
    }

    public Order() {
        this.customerId = this.getCustomerId();
        this.orderDate = LocalDateTime.now();
    }

    public Order(Integer customerId, double price) {
        this.customerId = customerId;
        this.price = price;
        this.orderDate = LocalDateTime.now();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public double getPrice() {
        return price;
    }

    public void addProductToBasket(Product product) {
         basket.add(product);
         renewPrice(product.getPrice());
    }

    public void removeProductFromBasket(Product product) {
         basket.remove(product);
         renewPrice(product.getPrice()*-1);
    }

    public void renewPrice(double price) {
        this.price = this.price + price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", basket=" + basket +
                ", price=" + price +
                '}';
    }
}
