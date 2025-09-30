package dao;

import datasource.Db;
import model.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCurrencyDao implements CurrencyDao {

    private static final String SQL_FIND_ALL =
            "SELECT code, name, rate_to_usd FROM currency ORDER BY code";
    private static final String SQL_FIND_BY_CODE =
            "SELECT code, name, rate_to_usd FROM currency WHERE code = ?";
    private static final String SQL_UPSERT =
            "INSERT INTO currency(code, name, rate_to_usd) VALUES(?,?,?) " +
                    "ON DUPLICATE KEY UPDATE name=VALUES(name), rate_to_usd=VALUES(rate_to_usd)";

    private final Db db;

    public JdbcCurrencyDao(Db db) { this.db = db; }

    @Override
    public List<Currency> findAll() throws Exception {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            List<Currency> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    @Override
    public Optional<Currency> findByCode(String code) throws Exception {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_CODE)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(map(rs)) : Optional.empty();
            }
        }
    }

    @Override
    public void upsert(Currency c) throws Exception {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPSERT)) {
            ps.setString(1, c.getCode());
            ps.setString(2, c.getName());
            ps.setDouble(3, c.getRateToUSD());
            ps.executeUpdate();
        }
    }

    private static Currency map(ResultSet rs) throws SQLException {
        return new Currency(
                rs.getString("code"),
                rs.getString("name"),
                rs.getDouble("rate_to_usd")
        );
    }
}
