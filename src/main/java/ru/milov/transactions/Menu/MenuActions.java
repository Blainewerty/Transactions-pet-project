package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.User;
import ru.milov.transactions.dao.domain.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuActions implements MenuFunc {

    DigestService digest = new DigestService();
    User user;
    String command;
    String email;
    String password;
    UserDao userDao = UserDao.getUserDao();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void view(String string) {
        System.out.println("For " + string + " type email, password");
    }

    @Override
    public void registration() throws IOException {
        view("registration");
        user = new User();
        email = reader.readLine();
        password = digest.digest(reader.readLine());
        user.setEmail(email);
        user.setPassword(password);
        userDao.insert(user);
        System.out.println(user);
    }

    @Override
    public void authentication() throws IOException {
        view("authentication");
        user = new User();
        email = reader.readLine();
        password = digest.digest(reader.readLine());
        user.setEmail(email);
        user.setPassword(password);
        userDao.findById(user);

        if (user.getId() != null) {
            do {
                System.out.println("What we do next?\n" +
                        "1: Add Info\n" +
                        "2: Get Current Info\n" +
                        "3: Go back");
                command = reader.readLine();
                switch (command) {
                    case "1":
                        SQLActions sqlActions = new SQLActions();
                        sqlActions.addInfoAboutUsersBillsToSQL(user);
                        break;
                    case "2":
                        System.out.println(user);
                        break;
                    case "3":
                        Menu menu = new Menu();
                        menu.start();
                }
            } while (!command.equals("3"));
        } else {
            System.out.println("Please check email and password and try again or register a new user\n");
        }
    }
}

