package dao;

import datasource.MariaDbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** JDBC implementation that reads from table currency(code, name, rate_to_usd). */
public class JdbcCurrencyDao implements CurrencyDao {

    private static final String SQL_RATE =
            "SELECT rate_to_usd FROM currency WHERE UPPER(code)=UPPER(?)";

    @Override
    public double getRate(String code) throws Exception {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("Currency code must not be empty.");

        try (Connection c = MariaDbConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_RATE)) {
            ps.setString(1, code.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        }
        throw new IllegalArgumentException("Unknown currency: " + code);
    }
}
