package productshop;

import productshop.entity.Customer;
import productshop.entity.Product;
import productshop.loyalitycard.Discount;
import productshop.service.OrderService;
import productshop.system.*;
import productshop.system.cache.CustomerCache;
import productshop.system.cache.ProductCache;

public class Main {

    public static void main(String[] args) {
        ApplicationStarter.start();
        System.out.println(Discount.getInstance().getCurrentActions());
        Customer customer =  CustomerCache.getCustomerById(1);
        customer.createOrder();
        Product product1 = ProductCache.findProductById(15);
        Product product2 = ProductCache.findProductById(16);
        Product product3 = ProductCache.findProductById(17);
        Product product4 = ProductCache.findProductById(18);
        customer.addProductToOrder(product1);
        customer.addProductToOrder(product3);
        customer.addProductToOrder(product2);
        customer.addProductToOrder(product4);
        customer.addProductToOrder(product2);
        customer.addProductToOrder(product2);
        InstanceHolder.getService(OrderService.class).confirmOrder(customer.getCurrentdOrder());

        ProductCache.showInfo();
    }
}
