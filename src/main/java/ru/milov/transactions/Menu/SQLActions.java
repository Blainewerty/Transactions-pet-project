package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.userbills.User;
import ru.milov.transactions.dao.domain.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQLActions {

    UserDao userDao = UserDao.getUserDao();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void getInfoAboutUserFromSQL(User user, User userbill) {
        userDao.findById(user, userbill);
        System.out.println(userbill + "\n");
    }

    public void addInfoAboutUsersBillsToSQL(User user, User userbill) throws IOException {
        userbill.setDate(String.valueOf(java.time.LocalDate.now()));
        System.out.println("Add score of transaction");
        userbill.setTransactions(Integer.parseInt(reader.readLine()));
        System.out.println("Add name of category\n" +
                "Salary, Funnies, Automobile, Health");
        userbill.setNameCategory(reader.readLine());
        getNameCategoryId(userbill);
        if (userbill.getTransactionsId() == 1) {
            userbill.setBalance(userbill.getBalance() + userbill.getTransactions());
        } else {
            userbill.setBalance(userbill.getBalance() - userbill.getTransactions());
        }
        userDao.update(user,userbill);
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
}
