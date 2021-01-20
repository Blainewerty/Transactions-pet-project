package ru.milov.transactions.dao;

import org.springframework.stereotype.Service;
import ru.milov.transactions.service.domain.UserBill;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Service
public class UserBillDao implements Dao <UserBill, Integer>{

    private final DataSource dataSource;

    public UserBillDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserBill findById(UserBill userBill) {

        String request = "select * from bills where user_id = ? and bill_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, userBill.getUser_id());
            statement.setInt(1, userBill.getBill_id());

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
    public List findByAll(UserBill userBill, List list) {
        String request = "select * from bills where user_id = ? order by bill_id";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, userBill.getUser_id());

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                userBill = new UserBill();
                userBill.setUser_id(rSet.getInt("user_id"));
                userBill.setBill_id(rSet.getInt("bill_id"));
                userBill.setName(rSet.getString("name"));
                userBill.setBalance(rSet.getInt("balance"));
                list.add(userBill);
            }
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public UserBill insert(UserBill userBill) {
        String request = "insert into bills (user_id, name, balance) values (?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(request,Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, userBill.getUser_id());
                ps.setString(2, userBill.getName());
                ps.setInt(3, userBill.getBalance());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating bill failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    userBill.setBill_id(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userBill;
    }

    @Override
    public UserBill insert(UserBill userBill, Connection connection) {
        return null;
    }

    @Override
    public UserBill update(UserBill userBill) {
        String request = "update bills set balance = ? where user_id = ? and bill_id = ?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, userBill.getBalance());
            statement.setInt(2, userBill.getUser_id());
            statement.setInt(3, userBill.getBill_id());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userBill;
    }

    @Override
    public boolean delete(Integer bill_id) {
        String request = "delete from bills where bill_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(request);

            ps.setInt(1, bill_id);

            ps.executeUpdate();

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
