package productshop.repository;

import productshop.ConnectionManager;
import productshop.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class OrderRepository {

    private final ConnectionManager connectionManager;

    public OrderRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // TODO полезность метода под сомнением
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
