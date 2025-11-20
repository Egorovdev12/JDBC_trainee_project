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

    public Order(Integer id, Integer customerId, LocalDateTime orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public Order(Integer customerId, LocalDateTime orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
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

    public boolean addProductToBasket(Product product) {
        return basket.add(product);
    }

    public boolean removeProductFromBasket(Product product) {
        return basket.remove(product);
    }

    public double calculatePrice() {
        double sum = 0;
        for (Product product : basket) {
            sum = sum + product.getPrice();
        }
        return sum;
    }
}
