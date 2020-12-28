package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.userbills.User;
import ru.milov.transactions.dao.domain.UserDao;
import ru.milov.transactions.dao.domain.userbills.UserPerson;
import ru.milov.transactions.dao.domain.userbills.UserSaving;
import ru.milov.transactions.dao.domain.userbills.UserWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MenuActions implements MenuFunc {

    DigestService digest = new DigestService();
    User user;
    String command;
    String email;
    String password;
    String billName;
    UserDao userDao = UserDao.getUserDao();
    SQLActions sqlActions = new SQLActions();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void view(String string) {
        System.out.println("For " + string + " type email, password");
    }


    @Override
    public void registration() throws IOException {
        user = new User();
        view("registration");
        email = reader.readLine();
        password = digest.digest(reader.readLine());
        user.setEmail(email);
        user.setPassword(password);
        userDao.insert(user);

    }

    @Override
    public void authentication() throws IOException {
        user = new User();
        do {
            view("authentication");
            email = reader.readLine();
            password = digest.digest(reader.readLine());
            user.setEmail(email);
            user.setPassword(password);
            userDao.getID(user);
            if (user.getId() == null) {
                System.out.println("Please check email and password and try again\n");
            }
        } while (user.getId() == null);
        System.out.println("Press name of Bill");
        String nameOfBill = reader.readLine();
        user.setNameOfBill(nameOfBill);
        switch (nameOfBill) {
            case "Person":
                UserPerson userPerson = new UserPerson();
                userDao.findById(user, userPerson);
                workingWithSQL(user, userPerson);
                break;
            case "Work":
                UserWork userWork = new UserWork();
                userDao.findById(user, userWork);
                workingWithSQL(user, userWork);
                break;
            case "Saving":
                UserSaving userSaving = new UserSaving();
                userDao.findById(user, userSaving);
                workingWithSQL(user, userSaving);
                break;
        }

    }


    private void workingWithSQL(User user, User userbill) throws IOException {
        do {
            System.out.println("What we do next?\n" +
                    "1: Add Info\n" +
                    "2: Get Current Info\n" +
                    "3: Get All operations\n" +
                    "4: Go back\n");
            command = reader.readLine();
            switch (command) {
                case "1":
                    sqlActions.addInfoAboutUsersBillsToSQL(user, userbill);
                    break;
                case "2":
                    sqlActions.getInfoAboutUserFromSQL(user, userbill);
                    break;
                case "3":
                    List<User> userList = new ArrayList<>();
                    userDao.findByAll(user, userbill, userList);
                    for (User userOperat : userList) {
                        System.out.println(userOperat);
                    }
                    break;
                case "4":
                    Menu menu = new Menu();
                    menu.start();
            }
        } while (!command.equals("4"));
    }
}


