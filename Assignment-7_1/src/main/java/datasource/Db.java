package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Centralized JDBC connection factory. */
public final class Db {
    private static final String DEFAULT_URL  = "jdbc:mariadb://localhost:3306/currencydb";
    private static final String DEFAULT_USER = "appuser";
    private static final String DEFAULT_PASS = "StrongPwd_123!";

    private final String url;
    private final String user;
    private final String pass;

    public Db() {
        this(
                System.getenv().getOrDefault("DB_URL",  DEFAULT_URL),
                System.getenv().getOrDefault("DB_USER", DEFAULT_USER),
                System.getenv().getOrDefault("DB_PASS", DEFAULT_PASS)
        );
    }
    public Db(String url, String user, String pass) {
        this.url = url; this.user = user; this.pass = pass;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
