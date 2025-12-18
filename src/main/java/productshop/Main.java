package productshop;

import productshop.entity.Customer;
import productshop.repository.CategoryRepository;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;
import productshop.repository.ProductRepository;
import productshop.service.CustomerService;
import productshop.service.OrderService;
import productshop.service.ProductService;
import productshop.settings.DatabaseSettings;
import productshop.system.ConnectionManager;
import productshop.system.DataDownloader;
import productshop.system.CustomerCash;
import productshop.system.ProductCash;

public class Main {
    public static void main(String[] args) {
        ConnectionManager testConnection = new ConnectionManager()
                .setUser(DatabaseSettings.USERNAME)
                .setPassword(DatabaseSettings.PASSWORD)
                .setUrl(DatabaseSettings.URL)
                .connect();

        DatabaseSettings testDatabaseSettings = new DatabaseSettings();
        CategoryRepository categoryRepository = new CategoryRepository(testConnection);
        ProductRepository productRepository = new ProductRepository(testConnection, categoryRepository);
        CustomerRepository customerRepository = new CustomerRepository(testConnection);
        OrderRepository orderRepository = new OrderRepository(testConnection);
        ProductService productService = new ProductService(productRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(customerRepository, orderRepository);
        DataDownloader dataDownloader = new DataDownloader(testConnection, categoryRepository);
        dataDownloader.start();
        CustomerCash.showInfo();
        ProductCash.showInfo();

       /* CustomerCash.getCUSTOMERS().get(1).createOrder();
        CustomerCash.getCUSTOMERS().get(1).getCurrentdOrder().addProductToBasket(ProductCash.getPRODUCTS().get(1));
        orderService.confirmOrder(CustomerCash.getCUSTOMERS().get(1).getCurrentdOrder());*/

        Customer customer = CustomerCash.getCustomerById(2);
        customer.createOrder();
        customer.addProductToOrder(ProductCash.findProductById(42));
        customer.addProductToOrder(ProductCash.findProductById(41));
        orderService.confirmOrder(customer.getCurrentdOrder());

    }
}
