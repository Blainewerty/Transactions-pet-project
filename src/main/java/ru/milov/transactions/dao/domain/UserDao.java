package ru.milov.transactions.dao.domain;

import ru.milov.transactions.dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.milov.transactions.dao.DaoFactory.getConnection;

public class UserDao implements Dao<User, Integer> {

    private static UserDao userDao;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    private UserDao() {

    }

    @Override
    public User findById(Integer id) {
        User user = null;
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select u.user_id, balance, date, name_category, transactions, name_bill\n" +
                    "from bills\n" +
                    "         inner join users u on bills.user_id = u.user_id\n" +
                    "         inner join transaction_categ tc on bills.transaction_categ_id = tc.transaction_categ_id\n" +
                    "        inner join name_bill nb on bills.name_bill_id = nb.name_bill_id\n" +
                    "where u.user_id = ? order by balance ");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setBalance(rs.getInt("balance"));
                user.setDate(rs.getString("date"));
                user.setNameCategory(rs.getString("name_category"));
                user.setTransactions(rs.getInt("transactions"));
                user.setNameOfBill(rs.getString("name_bill"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findByAll() {
        return null;
    }

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    public int getAccountSum(Integer userID) {
        return 0;
    }
}
