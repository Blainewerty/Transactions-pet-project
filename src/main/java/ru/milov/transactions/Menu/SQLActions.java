package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.userbills.User;
import ru.milov.transactions.dao.domain.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQLActions {

    UserDao userDao = UserDao.getUserDao();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void getInfoAboutUserFromSQL(User user) {
        System.out.println(user + "\n");
    }

    public void addInfoAboutUsersBillsToSQL(User user) throws IOException {
        user.setDate(String.valueOf(java.time.LocalDate.now()));
        System.out.println("Add score of transaction");
        user.setTransactions(Integer.parseInt(reader.readLine()));
        System.out.println("Add name of category\n" +
                "Salary, Funnies, Automobile, Health");
        user.setNameCategory(reader.readLine());
        System.out.println("Add name of bill\n" +
                "Person,Work,Saving");
        user.setNameOfBill(reader.readLine());
        getNameOfBill(user);
        getNameCategoryId(user);
        if (user.getTransactionsId() == 1) {
            user.setBalance(user.getBalance() + user.getTransactions());
        } else {
            user.setBalance(user.getBalance() - user.getTransactions());
        }
        userDao.update(user);
    }

    public User getNameCategoryId(User user) {
        switch (user.getNameCategory()) {
            case "Salary":
                user.setTransactionsId(1);
                break;
            case "Funnies":
                user.setTransactionsId(2);
                break;
            case "Health":
                user.setTransactionsId(3);
                break;
            case "Automobile":
                user.setTransactionsId(4);
                break;
        }
        return user;
    }

    public User getNameOfBill(User user) {
        switch (user.getNameOfBill()) {
            case "Person":
                user.setNameBillId(1);
                break;
            case "Work":
                user.setNameBillId(2);
                break;
            case "Saving":
                user.setNameBillId(3);
                break;
        }
        return user;
    }
}
