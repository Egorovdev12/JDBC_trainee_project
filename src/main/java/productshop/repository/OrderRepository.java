package productshop.repository;

import productshop.system.annotations.Repository;
import productshop.system.ConnectionManager;
import productshop.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class OrderRepository implements RepositoryInterface {

    private final ConnectionManager connectionManager;
    private final String ID = "id";
    private final String CUSTOMER_ID = "customer_id";
    private final String ORDER_DATE = "order_date";
    private final String TOTAL_PRICE = "total_price";

    private OrderRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder{
        static final OrderRepository INSTANCE = new OrderRepository(ConnectionManager.getInstance());
    }

    public static OrderRepository getInstance() {
        return OrderRepository.Holder.INSTANCE;
    }

    //TODO полезность метода под сомнением
    public Order save(Order orderForSave) {
        String sql = "INSERT INTO orders (customer_id, total_price) VALUES (?, ?)";
        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, orderForSave.getCustomerId());
            ps.setDouble(2, orderForSave.getPrice());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    return new Order(id);
                }
            }
        }
        catch (SQLException sqlException) {
            System.err.println("Ошибка при создании заказа. " + sqlException.getMessage());
        }
        return null;
    }

    public void confirmOrder(Order orderForUpdate) {
        String sql = "INSERT INTO orders (customer_id, total_price) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, orderForUpdate.getCustomerId());
            preparedStatement.setDouble(2, orderForUpdate.getPrice());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }
}
