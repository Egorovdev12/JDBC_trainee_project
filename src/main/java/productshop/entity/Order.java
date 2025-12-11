package productshop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODO Наверное нам все-таки нужен price заказа
// TODO сделаем прайс, который будет динамически меняться, в зависимости от того, сколько продуктов сейчас в корзине
public class Order {

    private Integer id;
    private final Integer customerId;
    private final LocalDateTime orderDate;
    private List<Product> basket = new ArrayList<>();
    private double price;

    public Order(Integer id) {
        this.id = id;
        this.customerId = this.getCustomerId();
        this.orderDate = LocalDateTime.now();    }

    public Order( ) {
        this.customerId = this.getCustomerId();
        this.orderDate = LocalDateTime.now();
    }

    public Order(Integer customerId, double price) {
        this.customerId = customerId;
        this.price = price;
        this.orderDate = LocalDateTime.now();
    }



    public Integer getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void addProductToBasket(Product product) {
         basket.add(product);
         renewPrice(product.getPrice());
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

    public void removeProductFromBasket(Product product) {
         basket.remove(product);
         renewPrice(product.getPrice()*-1);
    }

    public double getPrice() {
        return price;
    }

    /* public double calculatePrice() {
            double sum = 0;
            for (Product product : basket) {
                sum = sum + product.getPrice();
            }
            return sum;
        }*/
    public void renewPrice(double price) {
        this.price = this.price + price;
    }

}
