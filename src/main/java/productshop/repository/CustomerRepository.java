package productshop.repository;

import productshop.system.ConnectionManager;
import productshop.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements RepositoryInterface{

    private final ConnectionManager connectionManager;

    private final String ID = "id";
    private final String NAME = "name";
    private final String PHONE_NUMBER = "phone_number";
    private final String ORDER_COUNT = "order_count";
    private final String HAS_LOYALTY_CARD = "has_loyalty_card";

    private CustomerRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder{
        static final CustomerRepository INSTANCE = new CustomerRepository(ConnectionManager.getInstance());
    }

    public static CustomerRepository getInstance() {
        return CustomerRepository.Holder.INSTANCE;
    }



    public Customer findCustomerByName(String name) {
        String sql = "select * from customers where name=(?)";
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(ID);
            String customerPhone = resultSet.getString(PHONE_NUMBER);
            int order_count = resultSet.getInt(ORDER_COUNT);
            boolean hasLoyaltyCard = resultSet.getBoolean(HAS_LOYALTY_CARD);
            return new Customer(id, name, customerPhone, hasLoyaltyCard, order_count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Customer findCustomerById(int id) {
        String sql = "select * from customers where id=(?)";
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String customerName = resultSet.getString(NAME);
            String customerPhone = resultSet.getString(PHONE_NUMBER);
            int order_count = resultSet.getInt(ORDER_COUNT);
            boolean hasLoyaltyCard = resultSet.getBoolean(HAS_LOYALTY_CARD);
            return new Customer(id, customerName, customerPhone, hasLoyaltyCard, order_count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, phone_number, order_count, has_loyalty_card) VALUES(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhoneNumber());
            preparedStatement.setInt(3, customer.getOrderCount());
            preparedStatement.setBoolean(4, customer.isHasLoyaltyCard());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> findByOrderCount(int orderCount) {
        String sql = "SELECT * FROM customers WHERE order_count>=(?)";
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, orderCount);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                Boolean hasLoyaltyCard = resultSet.getBoolean(HAS_LOYALTY_CARD);
                int order_count = resultSet.getInt(ORDER_COUNT);
                customers.add(new Customer(id, name, phoneNumber, hasLoyaltyCard, order_count));
            }
            return customers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void changeOrderCount(int idCustomer) {
        String sql = "UPDATE customers SET order_count = order_count + 1 where id = (?)";
        try(PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, idCustomer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
