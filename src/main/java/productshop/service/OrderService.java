package productshop.service;

import productshop.entity.Product;
import productshop.loyalitycard.Action;
import productshop.loyalitycard.Discount;
import productshop.system.annotations.Service;
import productshop.entity.Order;
import productshop.exceptions.OrderIsNullException;
import productshop.repository.CustomerRepository;
import productshop.repository.OrderRepository;
import productshop.system.cache.CustomerCache;

import java.util.List;

@Service
public class OrderService implements ServiceInterface {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    private OrderService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    private static class Holder {
        static final OrderService INSTANCE = new OrderService(CustomerRepository.getInstance(), OrderRepository.getInstance());
    }

    public static OrderService getInstance() {
        return Holder.INSTANCE;
    }


    //TODO нужно избавиться от дублирования кода
    public void confirmOrder(Order order) {
        if (order == null) {
            throw new OrderIsNullException("Невозможно создать данный заказ, поскольку он null");
        }

        if (!checkLoyaltyCard(order)) {
            customerRepository.changeOrderCount(order.getCustomerId());
            orderRepository.confirmOrder(order);
            return;
        }

        List<Action> actions = Discount.getInstance().getCurrentActions();
        List<Product> basket = order.getBasket();

        for (int i = 0; i < actions.size(); i++) {
            for (int j = 0; j < basket.size(); j++) {
                Product productFromBasket = basket.get(j);
                if (actions.get(i).getCategory().equals(productFromBasket.getCategory())) {
                    Product productWithDiscount = new Product(
                            productFromBasket.getId(),
                            productFromBasket.getName(),
                            productFromBasket.getPrice() * (100 - actions.get(i).getCurrentDiscount()) / 100,
                            productFromBasket.getCategory());
                    basket.set(j, productWithDiscount);
                }
            }
        }

        order.renewPrice();
        customerRepository.changeOrderCount(order.getCustomerId());
        orderRepository.confirmOrder(order);
    }

    private boolean checkLoyaltyCard(Order order) {
        if (CustomerCache.getCustomerById(order.getCustomerId()).isHasLoyaltyCard()){
            return true;
        }
        return false;
    }
}
