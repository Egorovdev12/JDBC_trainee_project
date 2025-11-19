package productshop;

import productshop.entity.Customer;
import productshop.repository.CategoryRepository;
import productshop.repository.CustomerRepository;
import productshop.repository.ProductRepository;
import productshop.service.CustomerService;
import productshop.service.ProductService;

public class Main {
    public static void main(String[] args) {
        ConnectionManager testConnection = new ConnectionManager()
                .setUser("postgres")
                .setPassword("707070")
                .setUrl("jdbc:postgresql://localhost:4070/product_shop")
                .connect();

        CategoryRepository categoryRepository = new CategoryRepository(testConnection);
        ProductRepository productRepository = new ProductRepository(testConnection, categoryRepository);
        ProductService productService = new ProductService(productRepository);
        CustomerRepository customerRepository = new CustomerRepository(testConnection);
        CustomerService customerService = new CustomerService(customerRepository);
        customerService.addCustomer(new Customer("Иван", "89665432211", false));
    }
}
