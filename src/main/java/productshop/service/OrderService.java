package productshop.service;

import productshop.entity.Customer;
import productshop.entity.Order;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;

public class OrderService {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    public OrderService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public void confirmOrder(Order order) {
        if(order == null) {
            return;
        }
        customerRepository.changeOrderCount(order.getCustomerId());
        orderRepository.confirmOrder(order);
    }

}
