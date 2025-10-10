package dao;

import datasource.MariaDbJpaConnection;
import entity.Transaction;
import jakarta.persistence.EntityManager;

public class JpaTransactionDao implements TransactionDao {

    @Override
    public void insert(Transaction tx) throws Exception {
        if (tx == null) throw new IllegalArgumentException("Transaction is null.");
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        try {
            em.persist(tx);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }
}
