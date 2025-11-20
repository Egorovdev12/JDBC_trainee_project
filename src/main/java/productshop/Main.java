package productshop;

import productshop.entity.Order;
import productshop.repository.CategoryRepository;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;
import productshop.repository.ProductRepository;
import productshop.service.CustomerService;
import productshop.service.ProductService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ConnectionManager testConnection = new ConnectionManager()
                .setUser("postgres")
                .setPassword("707070")
                .setUrl("jdbc:postgresql://localhost:4070/product_shop")
                .connect();

        CategoryRepository categoryRepository = new CategoryRepository(testConnection);
        ProductRepository productRepository = new ProductRepository(testConnection, categoryRepository);
        CustomerRepository customerRepository = new CustomerRepository(testConnection);
        OrderRepository orderRepository = new OrderRepository(testConnection);

        ProductService productService = new ProductService(productRepository);
        CustomerService customerService = new CustomerService(customerRepository);

        orderRepository.save(new Order(2, LocalDateTime.now()));
    }
}
