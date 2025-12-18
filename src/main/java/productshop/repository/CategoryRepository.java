package productshop.repository;

import productshop.system.ConnectionManager;
import productshop.entity.category.Category;
import productshop.entity.category.CategoryDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private final ConnectionManager connectionManager;

    private final String ID = "id";
    private final String NAME = "name";

    public CategoryRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public CategoryDto save(Category category) {
        String sql = "INSERT INTO category (name) VALUES (?)";
        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.toString());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(ID);
                    return new CategoryDto(id, category.toString());
                }
            }
        }
        catch (SQLException exception) {
            System.err.println("Ошибка при выполнении запроса: " + exception.getMessage());
        }
        return null;
    }

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try (Statement statement = connectionManager.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                categories.add(Category.valueOf(rs.getString(NAME)));
            }
        }
        catch (SQLException exception) {
            System.err.println("Ошибка при выполнении запроса: " + exception.getMessage());
        }
        return categories;
    }

    public Integer getIdByName(String name) {
        String sql = "SELECT * FROM category WHERE name = (?)";
        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException exception) {
            System.err.println("Ошибка при выполнении запроса: " + exception.getMessage());
        }
        return null;
    }

    public Category getCategoryById(Integer id) {
        String sql = "SELECT * FROM category WHERE id = (?)";
        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Category.valueOf(resultSet.getString(2));
            }
        }
        catch (SQLException exception) {
            System.err.println("Ошибка при выполнении запроса: " + exception.getMessage());
        }
        return null;
    }
}
