package dao;

import datasource.MariaDbJpaConnection;
import entity.Currency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JpaCurrencyDao implements CurrencyDao {

    @Override
    public double getRate(String code) throws Exception {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("Currency code must not be empty.");

        EntityManager em = MariaDbJpaConnection.getInstance();
        Currency c = em.find(Currency.class, code.trim().toUpperCase());
        if (c == null) throw new IllegalArgumentException("Unknown currency: " + code);
        return c.getRateToUSD();
    }

    @Override
    public List<Currency> findAll() throws Exception {
        EntityManager em = MariaDbJpaConnection.getInstance();
        TypedQuery<Currency> q =
                em.createQuery("select c from Currency c order by c.code", Currency.class);
        return q.getResultList();
    }

    @Override
    public void insert(Currency currency) throws Exception {
        if (currency == null) throw new IllegalArgumentException("Currency is null.");
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        try {
            em.persist(currency);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }
}
