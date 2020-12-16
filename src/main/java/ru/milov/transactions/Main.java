package ru.milov.transactions;

import java.sql.*;
import java.sql.Date;
import java.util.Scanner;

public class Main {

    String url = "jdbc:postgresql://localhost:5432/postgres";
    String usr = "postgres";
    String pswd = "admin";
    Scanner in = new Scanner(System.in);
    int userId;
    int balance;

    public static void main(String[] args) {
        Main main = new Main();
        main.startApp();
    }

    private void startApp() {
        String email;
        String passwd;
        System.out.println("You want Sign in or Register? \nPress 1 for Sign in, 2 for Register");
        int choose = in.nextInt();
        switch (choose) {
            case 1: //User is exist , get info from database
                System.out.println("Place Name of email");
                in.nextLine();
                email = in.nextLine();
                System.out.println("Place Name of passwd");
                passwd = in.nextLine();
                System.out.println("You want add or get info?");
                choose = in.nextInt();
                if (choose == 1) {
                    addInfo(getUserID(email, passwd));
                } else {
                    getInformationFromSQL(getUserID(email, passwd));
                }
                break;
            case 2: //User does not exist, get new and add info
                System.out.println("Place Name of email");
                in.nextLine();
                email = in.nextLine();
                System.out.println("Place Name of passwd");
                passwd = in.nextLine();
                addUserToDataBase(email, passwd);
                addInfo(getUserID(email, passwd));
                break;
        }
    }

    private void getInformationFromSQL(int userID) {
        try (Connection conn = DriverManager.getConnection(url, usr, pswd)) {
            PreparedStatement statement = conn.prepareStatement("select email, balance, date, name_category, transactions\n" +
                    "from bills\n" +
                    "         inner join users u on bills.user_id = u.user_id\n" +
                    "         inner join transaction_categ tc on bills.transaction_categ_id = tc.transaction_categ_id\n" +
                    "where u.user_id = ? order by balance limit 1 ");

            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("email") + "\t" +
                        resultSet.getString("balance") + "\t" +
                        resultSet.getString("date") + "\t" +
                        resultSet.getString("transactions") + "\t" +
                        resultSet.getString("name_category"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    private void addInfo(int userID) {
        try (Connection conn = DriverManager.getConnection(url, usr, pswd)) {
            Date date = Date.valueOf(String.valueOf(java.time.LocalDate.now()));
            int transCat = 0;
            int nameBillId = 0;
            getBalance(userID);
            System.out.println("Add transaction");
            int transaction = in.nextInt();
            System.out.println("Set Transaction Category");
            in.nextLine();
            String transactionCategory = in.nextLine();
            System.out.println("Set Name Bill");
            String nameBill = in.nextLine();
            switch (nameBill) {
                case "Person":
                    nameBillId = 1;
                    break;
                case "Work":
                    nameBillId = 2;
                    break;
                case "Saving":
                    nameBillId = 3;
                    break;
            }
            switch (transactionCategory) {
                case "Salary":
                    transCat = 1;
                    break;
                case "Funnies":
                    transCat = 2;
                    break;
                case "Health":
                    transCat = 3;
                    break;
                case "Automobile":
                    transCat = 4;
                    break;
            }
            if (transCat == 1) {
                balance = balance + transaction;
            }else{if(balance != 0) balance = balance - transaction;}
            PreparedStatement statement = conn.prepareStatement("insert into bills (user_id, name_bill_id, balance, date, transactions, transaction_categ_id)\n" +
                    "values (?, ?, ?, ?, ?,?);\n");

            statement.setInt(1, userID);
            statement.setInt(2, nameBillId);
            statement.setInt(3, balance);
            statement.setDate(4, date);
            statement.setInt(5, transaction);
            statement.setInt(6, transCat);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getUserID(String email, String passwd) {

        DigestService hexpassword = new DigestService();
        String hexpasswrd = hexpassword.digest(passwd);
        try (Connection conn = DriverManager.getConnection(url, usr, pswd)) {

            PreparedStatement statement = conn.prepareStatement("select user_id from users where email = ? and password = ?");
            statement.setString(1, email);
            statement.setString(2, hexpasswrd);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userId;
    }

    private int getBalance (int userID){
        try (Connection conn = DriverManager.getConnection(url, usr, pswd)) {

            PreparedStatement statement = conn.prepareStatement("select balance from bills where user_id = ? order by balance limit 1");
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                balance = resultSet.getInt("balance");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return balance;
    }

    private void addUserToDataBase(String email, String passwd) {
        DigestService hexpassword = new DigestService();
        String hexpasswrd = hexpassword.digest(passwd);
        try (Connection conn = DriverManager.getConnection(url, usr, pswd)) {

            PreparedStatement statement = conn.prepareStatement("insert into users (email, password)\n" +
                    "values (?,?)");
            statement.setString(1, email);
            statement.setString(2, hexpasswrd);

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
