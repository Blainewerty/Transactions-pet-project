package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.domain.User;
import ru.milov.transactions.dao.domain.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Registration implements MenuFunc {
    DigestService digest = new DigestService();
    User user;
    String email;
    String password;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void view(){
        System.out.println("For registration type email and password");

    }

    @Override
    public void registration() throws IOException {
        user = new User();
        email = reader.readLine();
        password = digest.digest(reader.readLine());
        user.setEmail(email);
        user.setPassword(password);
    }

    @Override
    public void authentication() {

    }
}
