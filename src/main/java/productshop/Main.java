package productshop;

import productshop.entity.Customer;
import productshop.repository.CategoryRepository;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;
import productshop.repository.ProductRepository;
import productshop.service.CustomerService;
import productshop.service.OrderService;
import productshop.service.ProductService;
import productshop.service.ServiceInterface;
import productshop.settings.DatabaseSettings;
import productshop.system.*;

public class Main {
    public static void main(String[] args) {


         ProductService service =  InstanceHolder.getService(ProductService.class);


        System.out.println(service.toString());
        System.out.println(service.findProductByName("Творог"));

    }
}
