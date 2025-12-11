package productshop.service;

import productshop.entity.Order;
import productshop.exceptions.OrderIsNullException;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;

public class OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public OrderService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public void confirmOrder(Order order) {
        if (order == null) {
            throw new OrderIsNullException("Невозможно создать данный заказ, поскольку он null");
        }
        customerRepository.changeOrderCount(order.getCustomerId());
        orderRepository.confirmOrder(order);
    }
}
