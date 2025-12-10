package productshop;

import productshop.entity.Customer;
import productshop.entity.Order;
import productshop.entity.Product;
import productshop.entity.category.Category;
import productshop.repository.CategoryRepository;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;
import productshop.repository.ProductRepository;
import productshop.service.CustomerService;
import productshop.service.OrderService;
import productshop.service.ProductService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ConnectionManager testConnection = new ConnectionManager()
                .setUser("postgres")
                .setPassword("GermanLavr3010")
                .setUrl("jdbc:postgresql://localhost:5432/product_shop")
                .connect();

        CategoryRepository categoryRepository = new CategoryRepository(testConnection);
        ProductRepository productRepository = new ProductRepository(testConnection, categoryRepository);
        CustomerRepository customerRepository = new CustomerRepository(testConnection);
        OrderRepository orderRepository = new OrderRepository(testConnection);

        ProductService productService = new ProductService(productRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(customerRepository, orderRepository);

        Customer customer = new Customer(2, "Name", "79999", true, 0);
       /* customer.createOrder();
        *//*Если мы пытаемся вызвать addProduct2Order, и если мы до этого не вызвали метод createOrder, то такой вызов
        должен возвращать ексепшн с описанием проблемы*//*
        customer.addProductToOrder(new Product("aple", 100.00, Category.FRUITS));*/
/*
        customer.confirmOrder();
        после вызова метода сверху, информация о новом заказе отправляется в бд
*/

        customer.createOrder();
        customer.addProductToOrder(new Product("mikl", 100.00, Category.DAIRY));
        customer.addProductToOrder(new Product("aple", 300.00, Category.FRUITS));
        orderService.confirmOrder(customer.getCurrentdOrder());
        System.out.println(customer.getCurrentdOrder());

    }
}
