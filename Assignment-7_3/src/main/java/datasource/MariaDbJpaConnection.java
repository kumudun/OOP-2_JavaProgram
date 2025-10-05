package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/** Singleton-ish holder for a JPA EntityManager. */
public final class MariaDbJpaConnection {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private MariaDbJpaConnection() {}

    public static EntityManager getInstance() {
        if (em == null || !em.isOpen()) {
            if (emf == null || !emf.isOpen()) {
                emf = Persistence.createEntityManagerFactory("CurrencyMariaDbUnit");
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void closeAll() {
        try { if (em != null && em.isOpen()) em.close(); } catch (Exception ignore) {}
        try { if (emf != null && emf.isOpen()) emf.close(); } catch (Exception ignore) {}
        em = null; emf = null;
    }
}
