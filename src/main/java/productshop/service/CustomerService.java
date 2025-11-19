package productshop.service;

import productshop.entity.Customer;
import productshop.exceptions.LongNameInputException;
import productshop.repository.CustomerRepository;

import java.util.List;

public class CustomerService {

    private CustomerRepository customerRepository;

    private final Integer MAX_CUSTOMER_NAME_LENGTH = 60;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }

    public Customer findCustomerByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    public void addCustomer(Customer customer) {
        if(customer.getName().length() > MAX_CUSTOMER_NAME_LENGTH){
            throw new LongNameInputException("Длина имени превысила 60 символов");
        }
        customerRepository.addCustomer(customer);
    }
    public List<Customer> findByOrderCount(int orderCount) {
        return customerRepository.findByOrderCount(orderCount);
    }
}
