package productshop.system;

import productshop.repository.CategoryRepository;
import productshop.settings.DatabaseSettings;

import java.sql.*;

public class ConnectionManager {

    private String url;
    private String user;
    private String password;
    private Connection connection;

    private static class Holder{
        static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    private ConnectionManager() {
        this.url = DatabaseSettings.URL;
        this.user = DatabaseSettings.USERNAME;
        this.password = DatabaseSettings.PASSWORD;
        connect();
    }

    public static ConnectionManager getInstance(){
        return Holder.INSTANCE;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Успешное подключение к БД");

        }
        catch (SQLException exception) {
            System.out.println("Ошибка соединения");
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
