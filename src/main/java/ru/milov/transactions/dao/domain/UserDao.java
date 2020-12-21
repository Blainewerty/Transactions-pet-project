package ru.milov.transactions.dao.domain;

import ru.milov.transactions.dao.Dao;
import ru.milov.transactions.dao.domain.userbills.User;

import java.sql.*;
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
    public User findById(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select u.user_id, balance, date, name_category, transactions, name_bill\n" +
                    "from bills\n" +
                    "         inner join users u on bills.user_id = u.user_id\n" +
                    "         inner join transaction_categ tc on bills.transaction_categ_id = tc.transaction_categ_id\n" +
                    "         inner join name_bill nb on bills.name_bill_id = nb.name_bill_id\n" +
                    "where email = ? and password=? and name_bill=? group by u.user_id, balance, date, name_category, transactions, name_bill, bill_id\n" +
                    "order by bill_id");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNameOfBill());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
    public List<User> findByAll(User user, List <User> userList) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select u.user_id, balance, date, name_category, transactions, name_bill\n" +
                    "from bills\n" +
                    "         inner join users u on bills.user_id = u.user_id\n" +
                    "         inner join transaction_categ tc on bills.transaction_categ_id = tc.transaction_categ_id\n" +
                    "         inner join name_bill nb on bills.name_bill_id = nb.name_bill_id\n" +
                    "where email = ? and password=? and name_bill=? group by u.user_id, balance, date, name_category, transactions, name_bill, bill_id\n" +
                    "order by bill_id");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNameOfBill());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User userOperat = new User();
                userOperat.setId(rs.getInt("user_id"));
                userOperat.setEmail("email");
                userOperat.setBalance(rs.getInt("balance"));
                userOperat.setDate(rs.getString("date"));
                userOperat.setNameCategory(rs.getString("name_category"));
                userOperat.setTransactions(rs.getInt("transactions"));
                userOperat.setNameOfBill(rs.getString("name_bill"));
                userList.add(userOperat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }


    @Override
    public User insert(User user) {
        try (Connection connection = getConnection()) {

            PreparedStatement ps1 = connection.prepareStatement("insert into users (email, password) \n" +
                    "values (?,?)");
            ps1.setString(1, user.getEmail());
            ps1.setString(2, user.getPassword());

            ps1.execute();

            getID(user);

            PreparedStatement ps = connection.prepareStatement("insert into bills (user_id, name_bill_id, balance, date) \n" +
                    "values (?,?,0,?)");
            ps.setInt(1, user.getId());
            ps.setInt(2, user.getNameBillId());
            ps.setDate(3, Date.valueOf(user.getDate()));

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into bills (user_id, name_bill_id, balance, date, transactions, transaction_categ_id) \n" +
                    "values (?,?,?,?,?,?)");
            ps.setInt(1, user.getId());
            ps.setInt(2, user.getNameBillId());
            ps.setInt(3, user.getBalance());
            ps.setDate(4, Date.valueOf(user.getDate()));
            ps.setInt(5, user.getTransactions());
            ps.setInt(6, user.getTransactionsId());

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    public User getID(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select user_id from users where email=? and password=?");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("user_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
