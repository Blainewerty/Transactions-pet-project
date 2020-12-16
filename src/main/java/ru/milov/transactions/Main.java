package ru.milov.transactions;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Place Name of email");
        String email = in.nextLine();
        System.out.println("Place Name of passwd");
        String passwd = in.nextLine();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin")) {

            PreparedStatement statement = conn.prepareStatement("select email, balance, date, " +
                    "name_category, transactions\n" +
                    "from bills\n" +
                    "         inner join users u on bills.user_id = u.user_id\n" +
                    "         inner join transaction_categ tc on bills.transaction_categ_id = tc.transaction_categ_id\n" +
                    "where email = ? and password = ? ");
            statement.setString(1, email);
            statement.setString(2, passwd);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println((resultSet.getString("email") + "\t" +
                        resultSet.getInt("balance") + "\t" +
                        resultSet.getString("date") + "\t" +
                        resultSet.getString("name_category") + "\t" +
                        resultSet.getString("transactions")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
