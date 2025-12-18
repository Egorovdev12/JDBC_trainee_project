package productshop.system;

import productshop.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerCash {

    private static List<Customer> CUSTOMERS = new ArrayList<>();

    public static List<Customer> getCUSTOMERS() {
        return CUSTOMERS;
    }

    public static void setCUSTOMERS(List<Customer> CUSTOMERS) {
        CustomerCash.CUSTOMERS = CUSTOMERS;
    }

    public static void showInfo() {
        for (Customer customer : CUSTOMERS) {
            System.out.println(customer.toString());
        }
    }

    public static Customer getCustomerById(int id) {
        for (Customer customer : CUSTOMERS) {
            if(customer.getId()==id){
                return customer;
            }
        }
        return null;
    }
}
