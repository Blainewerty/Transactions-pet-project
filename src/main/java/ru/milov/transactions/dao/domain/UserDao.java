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
    public User findById(User user, User userBill) {
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
            ps.setString(3, userBill.getNameOfBill());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("user_id"));
                userBill.setBalance(rs.getInt("balance"));
                userBill.setDate(rs.getString("date"));
                userBill.setNameCategory(rs.getString("name_category"));
                userBill.setTransactions(rs.getInt("transactions"));
                userBill.setNameOfBill(rs.getString("name_bill"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findByAll(User user, User userBill, List<User> userList) {
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
            ps.setString(3, userBill.getNameOfBill());

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

            if (user.getId() == null) {
                PreparedStatement ps = connection.prepareStatement("insert into users (email, password) \n" +
                                "values (?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());

                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user, User userBill) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into bills (user_id, name_bill_id, balance, date, transactions, transaction_categ_id) \n" +
                    "values (?,?,?,?,?,?)");
            ps.setInt(1, user.getId());
            ps.setInt(2, userBill.getNameBillId());
            ps.setInt(3, userBill.getBalance());
            ps.setDate(4, Date.valueOf(userBill.getDate()));
            ps.setInt(5, userBill.getTransactions());
            ps.setInt(6, userBill.getTransactionsId());

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

}
