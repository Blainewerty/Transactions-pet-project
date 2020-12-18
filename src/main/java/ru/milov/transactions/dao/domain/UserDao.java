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
            PreparedStatement ps = connection.prepareStatement("select * from users where user_id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

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

    public int getAccountSum (Integer userID){
        return 0;
    }
}
