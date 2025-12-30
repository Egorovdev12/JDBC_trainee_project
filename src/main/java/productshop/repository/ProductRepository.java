package productshop.repository;

import productshop.system.ConnectionManager;
import productshop.entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository implements RepositoryInterface {

    private final ConnectionManager connectionManager;
    private final CategoryRepository categoryRepository;
    private final String ID = "id";
    private final String NAME = "name";
    private final String PRICE = "price";
    private final String CATEGORY_ID = "category_id";

    private ProductRepository(ConnectionManager connectionManager, CategoryRepository categoryRepository) {
        this.connectionManager = connectionManager;
        this.categoryRepository = categoryRepository;
    }
    private static class Holder{
        static final ProductRepository INSTANCE = new ProductRepository(ConnectionManager.getInstance(), CategoryRepository.getInstance());
    }

    public static ProductRepository getInstance() {
        return ProductRepository.Holder.INSTANCE;
    }

    public Product save(Product product) {
        String sql = "INSERT INTO product (name, price, category_id) VALUES (?, ?, ?)";

        Integer categoryId = categoryRepository.getIdByName(product.getCategory().toString());

        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, categoryId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys(); {
                    if (keys.next()) {
                        int generatedId = keys.getInt(1);
                        return new Product(generatedId, product.getName(), product.getPrice(), product.getCategory());
                    }
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Ошибка при выполнении запроса: " + exception.getMessage());
        }
        return null;
    }

    public Product findByName(String name) {
        String sql = "SELECT * FROM product WHERE product.name = (?)";
        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt(ID),
                            rs.getString(NAME),
                            rs.getDouble(PRICE),
                            categoryRepository.getCategoryById(rs.getInt(CATEGORY_ID))
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске: " + e.getMessage());
        }
        return null;
    }

    public void updatePrice(int id, double newPrice) {
        String sql = "UPDATE product SET price = ? WHERE id = ?";
        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении: " + e.getMessage());
        }
    }

    public void deleteByName(String name) {
        String sql = "DELETE FROM product WHERE name = ?";
        try (PreparedStatement ps = connectionManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении: " + e.getMessage());
        }
    }

    public void findAllAndPrint() {
        String sql = "SELECT id, name, price FROM product";
        try (Statement stmt = connectionManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt(ID) + " " +
                        rs.getString(NAME) + " " +
                        rs.getDouble(PRICE));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при чтении: " + e.getMessage());
        }
    }
}
