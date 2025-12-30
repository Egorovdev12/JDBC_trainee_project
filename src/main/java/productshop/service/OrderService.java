package productshop.service;

import productshop.entity.Order;
import productshop.exceptions.OrderIsNullException;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;

public class OrderService implements ServiceInterface {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    private OrderService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }
    private static class Holder{
        static final OrderService INSTANCE = new OrderService(CustomerRepository.getInstance(), OrderRepository.getInstance());
    }

    public static OrderService getInstance() {
        return Holder.INSTANCE;
    }

    public void confirmOrder(Order order) {
        if (order == null) {
            throw new OrderIsNullException("Невозможно создать данный заказ, поскольку он null");
        }
        customerRepository.changeOrderCount(order.getCustomerId());
        orderRepository.confirmOrder(order);
    }
}
