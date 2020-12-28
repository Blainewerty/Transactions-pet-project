package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.userbills.User;
import ru.milov.transactions.dao.domain.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQLActions {

    UserDao userDao = UserDao.getUserDao();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void getInfoAboutUserFromSQL(User user, User userBill) {
        userDao.findById(user, userBill);
        System.out.println(userBill + "\n");
    }

    public void addInfoAboutUsersBillsToSQL(User user, User userBill) throws IOException {
        userBill.setDate(String.valueOf(java.time.LocalDate.now()));
        System.out.println("Add score of transaction");
        userBill.setTransactions(Integer.parseInt(reader.readLine()));
        System.out.println("Add name of category\n" +
                "Salary, Funnies, Automobile, Health");
        userBill.setNameCategory(reader.readLine());
        getNameCategoryId(userBill);
        if (userBill.getTransactionsId() == 1) {
            userBill.setBalance(userBill.getBalance() + userBill.getTransactions());
        } else {
            userBill.setBalance(userBill.getBalance() - userBill.getTransactions());
        }
        userDao.update(user,userBill);
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
