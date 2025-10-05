package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton-like connection provider.
 * Reads env vars DB_URL, DB_USER, DB_PASS; falls back to your earlier defaults.
 */
public final class MariaDbConnection {

    private static Connection conn;

    private MariaDbConnection() {}

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            final String url  = getenvOrDefault("DB_URL",  "jdbc:mariadb://localhost:3306/currencydb");
            final String user = getenvOrDefault("DB_USER", "appuser");
            final String pass = getenvOrDefault("DB_PASS", "StrongPwd_123!");
            conn = DriverManager.getConnection(url, user, pass);
        }
        return conn;
    }

    public static void terminate() {
        if (conn != null) {
            try { conn.close(); } catch (SQLException ignored) {}
            conn = null;
        }
    }

    private static String getenvOrDefault(String key, String def) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? def : v;
    }
}
