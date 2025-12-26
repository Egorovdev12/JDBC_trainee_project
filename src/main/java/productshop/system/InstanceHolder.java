package productshop.system;

import productshop.repository.*;
import productshop.service.CustomerService;
import productshop.service.OrderService;
import productshop.service.ProductService;
import productshop.service.ServiceInterface;

import java.util.ArrayList;

public class InstanceHolder {

    private static ArrayList<RepositoryInterface> repositoryInstances = new ArrayList<>();

    private static ArrayList<ServiceInterface> serviceInstances = new ArrayList<>();

    static {
        repositoryInstances.add(CategoryRepository.getInstance());
        repositoryInstances.add(CustomerRepository.getInstance());
        repositoryInstances.add(OrderRepository.getInstance());
        repositoryInstances.add(ProductRepository.getInstance());
        serviceInstances.add(CustomerService.getInstance());
        serviceInstances.add(OrderService.getInstance());
        serviceInstances.add(ProductService.getInstance());
    }

    public static <T extends ServiceInterface> T getService(Class<T> serviceClass) {
        for (ServiceInterface service : serviceInstances) {
            if (serviceClass.isInstance(service)) {
                return serviceClass.cast(service); // безопасное приведение к T
            }
        }
        return null; // или throw new IllegalArgumentException("Service not found: " + serviceClass);
    }

}
