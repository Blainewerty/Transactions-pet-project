package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.userbills.User;
import ru.milov.transactions.dao.domain.UserDao;

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
        System.out.println("For " + string + " type email, password, bill");
    }

    @Override
    public void registration() throws IOException {
        user = new User();
        view("registration");
        email = reader.readLine();
        password = digest.digest(reader.readLine());
        billName = reader.readLine();
        user.setEmail(email);
        user.setPassword(password);
        user.setNameOfBill(billName);
        user.setDate((String.valueOf(java.time.LocalDate.now())));
        sqlActions.getNameOfBill(user);
        userDao.insert(user);

    }

    @Override
    public void authentication() throws IOException {
        user = new User();
        view("authentication");
        email = reader.readLine();
        password = digest.digest(reader.readLine());
        user.setEmail(email);
        user.setPassword(password);
        System.out.println("Press name of Bill");
        user.setNameOfBill(reader.readLine());
        userDao.findById(user);
        if (user.getId() != null) {
            do {

                System.out.println("What we do next?\n" +
                        "1: Add Info\n" +
                        "2: Get Current Info\n" +
                        "3: Get All operations\n" +
                        "4: Go back\n");
                command = reader.readLine();
                switch (command) {
                    case "1":
                        sqlActions.addInfoAboutUsersBillsToSQL(user);
                        break;
                    case "2":
                        sqlActions.getInfoAboutUserFromSQL(user);
                        break;
                    case "3":
                        List<User> userList = new ArrayList<>();
                        userDao.findByAll(user,userList);
                        for (User user: userList){
                            System.out.println(user);
                        }
                        break;
                    case "4":
                        Menu menu = new Menu();
                        menu.start();
                }
            } while (!command.equals("3"));
        } else {
            System.out.println("Please check email and password and try again or register a new user\n");
        }
    }
}

