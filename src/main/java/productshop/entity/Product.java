package productshop.entity;

import productshop.entity.category.Category;

public final class Product {

    private final Integer id;
    private final String name;
    private final double price;
    private final Category category;

    public Product(Integer id, String name, double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "productshop.entity.Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
