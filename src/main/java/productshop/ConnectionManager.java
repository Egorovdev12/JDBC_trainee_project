package productshop;

import java.sql.*;

public class ConnectionManager {

    private String url;
    private String user;
    private String password;
    private Connection connection;

    public ConnectionManager setUrl(String url) {
        this.url = url;
        return this;
    }

    public ConnectionManager setUser(String user) {
        this.user = user;
        return this;
    }

    public ConnectionManager setPassword(String password) {
        this.password = password;
        return this;
    }

    public ConnectionManager connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Успешное подключение к БД");
            return this;
        }
        catch (SQLException exception) {
            System.out.println("Ошибка соединения");
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }
}
