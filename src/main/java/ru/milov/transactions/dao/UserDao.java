package ru.milov.transactions.dao;

import org.springframework.stereotype.Service;
import ru.milov.transactions.service.entity.ServiceUser;
import ru.milov.transactions.service.entity.UserDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Service
public class UserDao implements Dao<UserDto, Long> {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ServiceUser findByEmail(String email) {
        String request = "select * from users where email = ?";
        ServiceUser serviceUser = null;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, email);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    serviceUser = new ServiceUser();
                    serviceUser.setId(rs.getLong("user_id"));
                    serviceUser.setEmail(rs.getString("email"));
                    serviceUser.setPassword(rs.getString("password"));
                    connection.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serviceUser;
    }

    @Override
    public UserDto findById(UserDto userDto) {

        String request = "select * from users where user_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setLong(1, userDto.getUser_id());

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                userDto.setFirstName(rSet.getString("first_name"));
                userDto.setLastName(rSet.getString("last_name"));
                userDto.setTotalBalance(rSet.getBigDecimal("total_balance"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userDto;
    }

    @Override
    public List<UserDto> findByAll(UserDto userDto, List<UserDto> transactionMap) {
        return null;
    }

    @Override
    public UserDto insert(UserDto userDto) {
        String request = "insert into users(email, password, first_name, last_name) values (?,?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            if (userDto.getUser_id() == null) {
                PreparedStatement ps = connection.prepareStatement(request,
                        Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, userDto.getEmail());
                ps.setString(2, userDto.getPassword());
                ps.setString(3, userDto.getFirstName());
                ps.setString(4, userDto.getLastName());

                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userDto.setUser_id(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userDto;
    }

    @Override
    public UserDto insert(UserDto userDto, Connection connection) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        String request = "update users set total_balance = (select sum(balance) from bills) where user_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setLong(1, userDto.getUser_id());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userDto;
    }

    @Override
    public boolean delete(Long user_id) {
        String request = "delete from users where user_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(request);

            ps.setLong(1, user_id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
