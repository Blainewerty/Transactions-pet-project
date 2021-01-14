package ru.milov.transactions.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.milov.transactions.service.domain.ServiceUser;
import ru.milov.transactions.service.domain.UserDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao implements Dao<UserDto, Integer> {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Logger log = LogManager.getLogger(UserDao.class.getName());

    public ServiceUser findByEmail(String email) {
        log.info("Start finding user in DB by email: " + email);
        String request = "select * from users where email = ?";
        ServiceUser serviceUser = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, email);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    serviceUser.setId(rs.getInt("user_id"));
                    serviceUser.setEmail(rs.getString("email"));
                    serviceUser.setPassword(rs.getString("password"));
                    log.trace("User with email " + email + " was found");
                }
            }
        } catch (SQLException throwables) {
            log.error("There is no such user in DB");
            throwables.printStackTrace();
        }
        return serviceUser;
    }

    @Override
    public UserDto findById(UserDto userDto) {
        log.info("Starting filling user by id");
        String request = "select * from bills where user_id = ?";
                try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, userDto.getId());


            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                userDto.setFirstName(rSet.getString("first_name"));
                userDto.setLastName(rSet.getString("last_name"));

                log.trace("Complete! " + userDto);
            }
        } catch (SQLException throwables) {
            log.error("Failure to filling user by id");
            throwables.printStackTrace();
        }
        return userDto;
    }

    @Override
    public List<UserDto> findByAll(UserDto userDto, List<UserDto> userDtoList) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("");
            ps.setInt(1, userDto.getId());


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDto userDtoOperat = new UserDto();
                userDtoOperat.setEmail("email");
                userDtoList.add(userDtoOperat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userDtoList;
    }

    @Override
    public UserDto insert(UserDto userDto) {
        String request = "insert into users(email, password, first_name, last_name) values (?,?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            if (userDto.getId() == null) {
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
                        userDto.setId(generatedKeys.getInt(1));
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
    public UserDto update(UserDto userDto) {

        System.out.println(userDto);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("");
            ps.setInt(1, userDto.getId());
            //ps.setTimestamp(4, Timestamp.valueOf(userDto.getDate()));


            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userDto;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }


}
