package productshop.entity;

import productshop.system.cache.ProductCache;

public class Customer {
    private Integer id;
    private String name;
    private String phoneNumber;
    private int orderCount;
    private boolean hasLoyaltyCard;
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

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
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

    public void createOrder() {
        currentdOrder = new Order(this.id, 0);
    }

    public void addProductToOrder(Product product) {
        if (currentdOrder == null) {
            throw new NullPointerException("Ошибка, заказа не существует");
        }
        if(ProductCache.findProductById(product.getId()) == null){
            throw new NullPointerException("Продукта не существует");
        }
        currentdOrder.addProductToBasket(product);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderCount=" + orderCount +
                ", hasLoyaltyCard=" + hasLoyaltyCard +
                '}';
    }
}
