package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.User;
import ru.milov.transactions.dao.domain.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQLActions {

    UserDao userDao = UserDao.getUserDao();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
        if(user.getNameCategory().equals("Salary")){
            user.setBalance(user.getBalance() + user.getTransactions());
        }else{
            user.setBalance(user.getBalance() - user.getTransactions());
        }
        userDao.insert(user);
    }
}
