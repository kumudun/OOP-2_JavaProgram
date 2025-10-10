package dao;

import entity.Transaction;

public interface TransactionDao {
    void insert(Transaction tx) throws Exception;
}
