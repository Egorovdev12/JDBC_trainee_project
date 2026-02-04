package productshop.system;

import productshop.entity.Customer;
import productshop.entity.Product;
import productshop.entity.category.Category;
import productshop.loyalitycard.Action;
import productshop.loyalitycard.Discount;
import productshop.repository.CategoryRepository;
import productshop.system.cache.CustomerCache;
import productshop.system.cache.ProductCache;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataDownloader {

    private final ConnectionManager connectionManager;
    private final String ID = "id";
    private final String NAME = "name";
    private final String PRICE = "price";
    private final String CATEGORY_ID = "category_id";
    private final String PHONE_NUMBER = "phone_number";
    private final String ORDER_COUNT = "order_count";
    private final String HAS_LOYALTY_CARD = "has_loyalty_card";
    private final String CURRENTDISCOUNT = "currentdiscount";
    private final String CATEGORY = "category";
    private final String STARTDATE = "startdate";
    private final String ENDDATE = "enddate";

    public DataDownloader(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void downloadDataAboutAll() {
        downloadCustomers();
        downloadProducts();
        downloadActions();
    }

    public void downloadActions() {
        String sql = "SELECT * from action WHERE current_date between startdate and enddate";
        try(Statement statement = connectionManager.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Discount.getInstance().addAction(new Action(resultSet.getDouble(CURRENTDISCOUNT),
                        Category.valueOf(resultSet.getString(CATEGORY)),
                        resultSet.getDate(STARTDATE).toLocalDate(),
                        resultSet.getDate(ENDDATE).toLocalDate()));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void downloadProducts() {
        CategoryRepository categoryRepository = CategoryRepository.getInstance();
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";
        try(Statement statement = connectionManager.getConnection().createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                products.add(new Product(
                        rs.getInt(ID),
                        rs.getString(NAME),
                        rs.getDouble(PRICE),
                        categoryRepository.getCategoryById(rs.getInt(CATEGORY_ID))
                ));
            }
            ProductCache.setPRODUCTS(products);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    private void downloadCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customers";
        try(Statement statement = connectionManager.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                customers.add(new Customer(rs.getInt(ID), rs.getString(NAME), rs.getString(PHONE_NUMBER), rs.getBoolean(HAS_LOYALTY_CARD),
                        rs.getInt(ORDER_COUNT)));
                System.out.println();
            }
            CustomerCache.setCUSTOMERS(customers);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
