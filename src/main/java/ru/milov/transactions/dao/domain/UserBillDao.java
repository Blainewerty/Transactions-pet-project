package ru.milov.transactions.dao.domain;

import ru.milov.transactions.dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.milov.transactions.dao.DaoFactory.getConnection;

public class UserBillDao implements Dao<UserBills, Integer>  {

    private static UserBillDao userBillDao;

    public static UserBillDao getUserDao() {
        if (userBillDao == null) {
            userBillDao = new UserBillDao();
        }
        return userBillDao;
    }

    private UserBillDao(){

    }

    @Override
    public UserBills findById(Integer id) {
        UserBills userBills = null;
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
                userBills = new UserBills();
                userBills.setId(rs.getInt("user_id"));
                userBills.setBalance(rs.getInt("balance"));
                userBills.setDate(rs.getString("date"));
                userBills.setNameCategory(rs.getString("name_category"));
                userBills.setTransactions(rs.getInt("transactions"));
                userBills.setNameOfBill(rs.getString("name_bill"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userBills;
    }

    @Override
    public List<UserBills> findByAll() {
        return null;
    }

    @Override
    public UserBills insert(UserBills userBills) {
        return null;
    }

    @Override
    public UserBills update(UserBills userBills) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
