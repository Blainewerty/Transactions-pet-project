package ru.milov.transactions.dao;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DaoFactory {

    private static UserDao userDao;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao(getDataSource());
        }
        return userDao;
    }

    private static UserBillDao userBillDao;

    public static UserBillDao getUserBillDao() {
        if (userBillDao == null) {
            userBillDao = new UserBillDao(getDataSource());
        }
        return userBillDao;
    }

    private static TransactionDao transactionDao;

    public static TransactionDao getTransactionDao() {
        if (transactionDao == null) {
            transactionDao = new TransactionDao(getDataSource());
        }
        return transactionDao;
    }

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            ds.setUsername("postgres");
            ds.setPassword("admin");

            dataSource = ds;
        }
        return dataSource;
    }
}
