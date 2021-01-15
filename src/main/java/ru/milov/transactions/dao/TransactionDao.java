package ru.milov.transactions.dao;

import ru.milov.transactions.service.domain.Transaction;
import ru.milov.transactions.service.domain.UserBill;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class TransactionDao implements Dao<Transaction, Integer> {

    private final DataSource dataSource;

    public TransactionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Transaction findById(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findByAll(Transaction transaction, List<Transaction> list) {
        String request = "select * from transaction where user_id = ? and bill_id = ? order by transaction_id";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, transaction.getUser_id());
            statement.setInt(2, transaction.getBill_id());

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                transaction = new Transaction();
                transaction.setTransaction_id(rSet.getInt("transaction_id"));
                transaction.setUser_id(rSet.getInt("user_id"));
                transaction.setBill_id(rSet.getInt("bill_id"));
                transaction.setNameOfTransaction(rSet.getString("transaction_name"));
                transaction.setDate(String.valueOf(rSet.getTimestamp("transaction_date")));
                transaction.setValueOfTransaction(rSet.getInt("transaction_value"));
                list.add(transaction);
            }
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Transaction insert(Transaction transaction) {
        String request = "insert into transaction (user_id, bill_id, transaction_name, transaction_date, transaction_value) " +
                "values (?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, transaction.getUser_id());
            ps.setInt(2, transaction.getBill_id());
            ps.setString(3, transaction.getNameOfTransaction());
            ps.setTimestamp(4, Timestamp.valueOf(transaction.getDate()));
            ps.setInt(5,transaction.getValueOfTransaction());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating transaction failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    transaction.setTransaction_id(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating transaction failed, no ID obtained.");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transaction;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
