package ru.milov.transactions.dao;

import ru.milov.transactions.service.domain.UserBill;

import javax.sql.DataSource;

import java.sql.*;
import java.util.List;

public class UserBillDao implements Dao <UserBill, Integer>{

    private final DataSource dataSource;

    public UserBillDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public UserBill findById(UserBill userBill) {

        String request = "select * from bills where user_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, userBill.getId());

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                userBill.setName(rSet.getString("name"));
                userBill.setBalance(rSet.getInt("balance"));
            }
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return userBill;
    }

    @Override
    public List findByAll(UserBill user, List list) {
        return null;
    }

    @Override
    public UserBill insert(UserBill userBill) {
        String request = "insert into bills (user_id, name, balance) values (?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(request);

                ps.setInt(1, userBill.getId());
                ps.setString(2, userBill.getName());
                ps.setInt(3, userBill.getBalance());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userBill;
    }

    @Override
    public UserBill update(UserBill o) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

}
