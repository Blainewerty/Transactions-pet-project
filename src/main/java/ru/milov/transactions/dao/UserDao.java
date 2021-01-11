package ru.milov.transactions.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.milov.transactions.domain.ServiceUser;
import ru.milov.transactions.domain.UserDto;

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
                    serviceUser = new ServiceUser();
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
        String request = "select u.user_id, balance, date, name_category, transactions, name_bill\n" +
                "from bills\n" +
                "         inner join users u on bills.user_id = u.user_id\n" +
                "         inner join transaction_categ tc on bills.transaction_categ_id = tc.transaction_categ_id\n" +
                "         inner join name_bill nb on bills.name_bill_id = nb.name_bill_id\n" +
                "where u.user_id = ? and name_bill = ? group by u.user_id, balance, date, name_category, transactions, name_bill, bill_id\n" +
                "order by bill_id";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, userDto.getId());
            statement.setString(2, userDto.getNameOfBill());

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                userDto.setBalance(rSet.getInt("balance"));
                userDto.setDate(rSet.getString("date"));
                userDto.setNameCategory(rSet.getString("name_category"));
                userDto.setLastTransaction(rSet.getInt("transactions"));

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
            PreparedStatement ps = connection.prepareStatement("select u.user_id, balance, date, name_category, transactions, name_bill\n" +
                    "from bills\n" +
                    "         inner join users u on bills.user_id = u.user_id\n" +
                    "         inner join transaction_categ tc on bills.transaction_categ_id = tc.transaction_categ_id\n" +
                    "         inner join name_bill nb on bills.name_bill_id = nb.name_bill_id\n" +
                    "where u.user_id = ? and name_bill=? group by u.user_id, balance, date, name_category, transactions, name_bill, bill_id\n" +
                    "order by bill_id");
            ps.setInt(1, userDto.getId());
            ps.setString(2, userDto.getNameOfBill());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDto userDtoOperat = new UserDto();
                userDtoOperat.setEmail("email");
                userDtoOperat.setBalance(rs.getInt("balance"));
                userDtoOperat.setDate(rs.getString("date"));
                userDtoOperat.setNameCategory(rs.getString("name_category"));
                userDtoOperat.setLastTransaction(rs.getInt("transactions"));
                userDtoOperat.setNameOfBill(rs.getString("name_bill"));
                userDtoList.add(userDtoOperat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userDtoList;
    }


    @Override
    public UserDto insert(UserDto userDto) {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println(userDto);
            if (userDto.getId() == null) {
                PreparedStatement ps = connection.prepareStatement("insert into users (email, password) \n" +
                                "values (?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, userDto.getEmail());
                ps.setString(2, userDto.getPassword());

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
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into bills (user_id, name_bill_id, balance, date, transactions, transaction_categ_id) \n" +
                    "values (?,?,?,?,?,?)");
            ps.setInt(1, userDto.getId());
//            ps.setInt(2, userBill.getNameBillId());
//            ps.setInt(3, userBill.getBalance());
//            ps.setDate(4, Date.valueOf(userBill.getDate()));
//            ps.setInt(5, userBill.getTransactions());
//            ps.setInt(6, userBill.getTransactionsId());

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
